package com.poec.projet_backend.user_app;

import com.poec.projet_backend.domain.profile.Profile;
import com.poec.projet_backend.domain.profile.ProfileDTO;

import java.util.List;

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
                userApp.getNickname(),
                userApp.getEmail(),
                userApp.getRole(),
                userApp.getProfile().getId()
        );
    }
}
