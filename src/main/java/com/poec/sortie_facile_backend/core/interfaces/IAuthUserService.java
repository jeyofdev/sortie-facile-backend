package com.poec.sortie_facile_backend.core.interfaces;

import com.poec.sortie_facile_backend.auth_user.AuthUser;
import com.poec.sortie_facile_backend.auth_user.dto.AuthUserDTO;
import org.springframework.security.access.AccessDeniedException;

import java.util.List;

public interface IAuthUserService {

    public List<AuthUser> findAll() throws AccessDeniedException;

    public AuthUser findUserByEmail(String email);

    public AuthUser findUserById(Long id);
}
