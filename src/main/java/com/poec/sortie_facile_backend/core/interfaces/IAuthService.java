package com.poec.sortie_facile_backend.core.interfaces;

import com.poec.sortie_facile_backend.auth.model.AuthResponse;
import com.poec.sortie_facile_backend.auth.model.LoginRequest;
import com.poec.sortie_facile_backend.auth.model.RegisterRequest;
import com.poec.sortie_facile_backend.exceptions.UsernameAlreadyTakenException;

import java.util.Map;

public interface IAuthService {

    public Map<String, String> register(RegisterRequest request) throws UsernameAlreadyTakenException;

    public AuthResponse login(LoginRequest request);
}
