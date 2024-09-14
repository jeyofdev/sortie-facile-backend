package com.poec.sortie_facile_backend.domain.profile.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record ProfileDTO(
        Long id,
        String firstname,
        String lastname,
        String streetNumber,
        String street,
        String postalCode,
        String description,
        String avatar,
        String phone,
        LocalDate dateOfBirth,
        List<Long> activityIds,
        Long regionId,
        Long departmentId,
        Long cityId,
        Long userId,
        List<Long> bookingIds,
        List<Long> categoryIds
) {

}
