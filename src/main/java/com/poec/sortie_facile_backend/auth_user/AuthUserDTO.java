package com.poec.sortie_facile_backend.user_app;

public record UserAppDTO(
        Long id,
        String nickname,
        String email,
        String role,
        Long profileId

) {
    public static UserAppDTO mapFromEntity(UserApp userApp) {
        return new UserAppDTO(
                userApp.getId(),
                userApp.getUsername(),
                userApp.getEmail(),
                userApp.getRole(),
                userApp.getProfile().getId()
        );
    }
}
