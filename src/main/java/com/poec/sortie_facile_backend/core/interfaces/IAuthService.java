package com.poec.sortie_facile_backend.core.interfaces;

import com.poec.sortie_facile_backend.auth.model.AuthResponse;
import com.poec.sortie_facile_backend.auth.model.LoginRequest;
import com.poec.sortie_facile_backend.auth.model.RegisterRequest;
import com.poec.sortie_facile_backend.auth.model.ResetPasswordResponse;
import com.poec.sortie_facile_backend.auth_user.AuthUser;
import com.poec.sortie_facile_backend.exceptions.UsernameAlreadyTakenException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public interface IAuthService {

    public Map<String, String> register(RegisterRequest request) throws UsernameAlreadyTakenException;

    public AuthResponse login(LoginRequest request);

    public AuthResponse requestPasswordReset(String email);

    public ResetPasswordResponse resetPassword(String token, String newPassword);
}
