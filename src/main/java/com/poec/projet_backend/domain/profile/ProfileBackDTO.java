package com.poec.projet_backend.domain.profile;

import java.util.List;

public record ProfileBackDTO(

        String firstname,
        String lastname,
        String streetNumber,
        String street,
        int postalCode,
        String description,
        String avatar,
        String phone,
        String dateOfBirth,
        List<Long> categoryIds,
        List<Long> bookingIds

       ) {
    public static Profile mapToEntity(ProfileBackDTO profileBackDTO) {
        return new Profile(
                profileBackDTO.firstname(),
                profileBackDTO.lastname(),
                profileBackDTO.streetNumber(),
                profileBackDTO.street(),
                profileBackDTO.postalCode(),
                profileBackDTO.description(),
                profileBackDTO.avatar(),
                profileBackDTO.phone(),
                profileBackDTO.dateOfBirth()
        );
    }
}
