package com.poec.sortie_facile_backend.auth_user.dto;

public record AuthUserDTO(
        Long id,
        String nickname,
        String email,
        String role
       /* Long profileId*/

) {
}
