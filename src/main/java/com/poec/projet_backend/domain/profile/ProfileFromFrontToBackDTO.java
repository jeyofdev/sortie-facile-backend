package com.poec.projet_backend.domain.profile;

import java.util.List;
import java.util.Optional;

public record ProfileFromFrontToBackDTO(

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
        List<Long> bookingIds,
        Optional<Long> userId

       ) {
    public static Profile mapToEntity(ProfileFromFrontToBackDTO profileBackDTO) {
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
