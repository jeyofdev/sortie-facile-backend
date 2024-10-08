package com.poec.sortie_facile_backend.auth;

import com.poec.sortie_facile_backend.auth.model.LoginRequest;
import com.poec.sortie_facile_backend.auth.model.AuthResponse;
import com.poec.sortie_facile_backend.auth.model.RegisterRequest;
import com.poec.sortie_facile_backend.auth.model.ResetPasswordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest request) {
        Map<String, String> registerResponse = authService.register(request);
        return new ResponseEntity<>(registerResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        AuthResponse authenticationResponse = authService.login(request);
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<AuthResponse> requestPasswordReset(@RequestParam String email) {
        AuthResponse requestPasswordResetResponse = authService.requestPasswordReset(email);
        return new ResponseEntity<>(requestPasswordResetResponse, HttpStatus.OK);
    }

    @PostMapping("/update-password")
    public ResponseEntity<ResetPasswordResponse> resetPassword(
            @RequestParam String token,
            @RequestParam String newPassword
    ) {
        ResetPasswordResponse resetPasswordResponse = authService.resetPassword(token, newPassword);
        return new ResponseEntity<>(resetPasswordResponse, HttpStatus.OK);
    }
}
