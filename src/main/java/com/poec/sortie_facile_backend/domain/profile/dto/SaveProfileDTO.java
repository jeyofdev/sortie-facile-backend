package com.poec.sortie_facile_backend.domain.profile.dto;

import jakarta.annotation.Nullable;

import java.time.LocalDate;
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
        LocalDate dateOfBirth,
        @Nullable List<Long> categoryIds,
        @Nullable Long regionId,
        @Nullable Long departmentId,
        @Nullable Long cityId
) {

}
