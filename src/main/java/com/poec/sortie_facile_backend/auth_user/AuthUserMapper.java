package com.poec.sortie_facile_backend.auth_user;

import com.poec.sortie_facile_backend.auth_user.dto.AuthUserDTO;

public class AuthUserMapper {
    public static AuthUserDTO mapFromEntity(AuthUser authUser) {
        return new AuthUserDTO(
                authUser.getId(),
                authUser.getUsername(),
                authUser.getEmail(),
                authUser.getRole()
        );
    }
}
