package com.poec.sortie_facile_backend.auth;

import com.poec.sortie_facile_backend.auth.model.LoginRequest;
import com.poec.sortie_facile_backend.auth.model.AuthResponse;
import com.poec.sortie_facile_backend.auth.model.RegisterRequest;
import com.poec.sortie_facile_backend.auth.model.ResetPasswordResponse;
import com.poec.sortie_facile_backend.core.interfaces.IAuthService;
import com.poec.sortie_facile_backend.exceptions.UsernameAlreadyTakenException;
import com.poec.sortie_facile_backend.auth_user.AuthUser;
import com.poec.sortie_facile_backend.auth_user.AuthUserRepository;
import com.poec.sortie_facile_backend.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final AuthUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;

    @Override
    public Map<String, String> register(RegisterRequest request) throws UsernameAlreadyTakenException {

        if (repository.findByEmail(request.getEmail()).isEmpty()) {
            var user = AuthUser.builder()
                    .nickname(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role("ROLE_" + request.getRequiredRole())
                    .build();

            repository.save(user);

            // response to client
            Map<String, String> body = new HashMap<>();
            body.put("message", "Account successfully created as user");
            body.put("userId", String.valueOf(user.getId()));

            return body;

        } else {
            throw new UsernameAlreadyTakenException("Username already taken");
        }
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        // check credentials
        // if the user was found
        // check that the user is authorized to access protected resources
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            // get user by email
            AuthUser user = repository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found in Database"));

            // extract user infos
            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("role", user.getRole());
            extraClaims.put("id", user.getId());
            extraClaims.put("nickname", user.getNickname());

            // generate token with role
            String jwtToken = jwtService.generateToken(new HashMap<>(extraClaims), user, 60 * 60 * 1000);
            return AuthResponse.builder()
                    .token(jwtToken)
                    .message("Logged In")
                    .build();

        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Login failed. Please verify your credentials and try again.");
        }
    }

    public AuthResponse requestPasswordReset(String email) {
        // get user by email
        AuthUser user = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // create additional claims for the reset token
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("type", "reset");

        // generate and save token
        String jwtToken = jwtService.generateToken(extraClaims, user, 15 * 60 * 1000);

        user.setResetToken(jwtToken);
        user.setResetTokenExpiration(LocalDateTime.now().plusMinutes(15));
        repository.save(user);

        // send password reset email
        emailService.sendPasswordResetEmail(user.getEmail(), jwtToken);

        // return token
        return AuthResponse.builder()
                .token(jwtToken)
                .message("An email containing a link to reset your password has been sent to your address. Please check your inbox and follow the instructions.")
                .build();
    }

    public ResetPasswordResponse resetPassword(String token, String newPassword) {
        // check token
        AuthUser user = repository.findByResetToken(token)
                .orElseThrow(() -> new IllegalStateException("Invalid token"));

        if (user.getResetTokenExpiration().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token has expired");
        }

        // update password
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setResetTokenExpiration(null);

        repository.save(user);

        return ResetPasswordResponse.builder()
                .message("Your password has been updated successfully. You can now use your new password to log in.")
                .build();
    }
}
