package com.poec.sortie_facile_backend.auth;

import com.poec.sortie_facile_backend.auth.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        RegisterResponse registerResponse = authService.register(request);
        return new ResponseEntity<>(registerResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        AuthResponse authenticationResponse = authService.login(request);
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<MessageResponse> requestPasswordReset(@RequestParam("email") String email) {
        MessageResponse requestPasswordResetResponse = authService.requestPasswordReset(email);
        return new ResponseEntity<>(requestPasswordResetResponse, HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<MessageResponse> resetPassword(
            @RequestParam("resetToken") String resetToken,
            @RequestParam("newPassword") String newPassword
    ) {
        MessageResponse messageResponse = authService.resetPassword(resetToken, newPassword);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PostMapping("/update-password")
    public ResponseEntity<MessageResponse> updatePassword(
            @RequestBody UpdatePasswordRequest changePasswordRequest
    ) {
        MessageResponse messageResponse = authService.updatePassword(
                changePasswordRequest.getOldPassword(),
                changePasswordRequest.getNewPassword()
        );

        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping("/verification-account")
    public ResponseEntity<MessageResponse> validateAccount(@RequestParam("verificationToken") String verificationToken) {
        MessageResponse messageResponse = authService.validateAccount(verificationToken);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
