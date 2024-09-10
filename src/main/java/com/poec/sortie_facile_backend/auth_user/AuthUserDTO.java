package com.poec.sortie_facile_backend.auth_user;

public record AuthUserDTO(
        Long id,
        String nickname,
        String email,
        String role,
        Long profileId

) {
    public static AuthUserDTO mapFromEntity(AuthUser authUser) {
        return new AuthUserDTO(
                authUser.getId(),
                authUser.getUsername(),
                authUser.getEmail(),
                authUser.getRole(),
                authUser.getProfile().getId()
        );
    }
}
