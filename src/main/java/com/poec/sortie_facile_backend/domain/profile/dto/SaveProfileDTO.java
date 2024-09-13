package com.poec.sortie_facile_backend.domain.profile.dto;

import java.util.List;

public record SaveProfileDTO(
        String firstname,
        String lastname,
        String streetNumber,
        String street,
        String postalCode,
        String description,
        String avatar,
        String phone,
        String dateOfBirth,
        List<Long> categoryIds
) {

}
