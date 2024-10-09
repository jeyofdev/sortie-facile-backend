package com.poec.sortie_facile_backend.core.interfaces;

import com.poec.sortie_facile_backend.auth.model.*;
import com.poec.sortie_facile_backend.exceptions.UsernameAlreadyTakenException;

import java.util.Map;

public interface IAuthService {

    public RegisterResponse register(RegisterRequest request) throws UsernameAlreadyTakenException;

    public AuthResponse login(LoginRequest request);

    public MessageResponse requestPasswordReset(String email);

    public MessageResponse resetPassword(String token, String newPassword);
}
