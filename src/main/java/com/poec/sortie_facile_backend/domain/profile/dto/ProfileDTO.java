package com.poec.sortie_facile_backend.domain.profile.dto;

import java.util.List;

public record ProfileDTO(
        Long id,
        String firstname,
        String lastname,
        String streetNumber,
        String street,
        @jakarta.validation.constraints.NotNull(message = "The postal code field is required.") @jakarta.validation.constraints.Pattern(regexp = "\\d{5}", message = "The postal code must be exactly 5 digits.") String postalCode,
        String description,
        String avatar,
        String phone,
        String dateOfBirth,
        List<Long> activityIds,
        Long regionId,
        Long departmentId,
        Long cityId,
        Long userId,
        List<Long> bookingIds,
        List<Long> categoryIds
) {

}
