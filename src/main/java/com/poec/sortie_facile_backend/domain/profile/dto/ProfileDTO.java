package com.poec.sortie_facile_backend.domain.profile.dto;

import com.poec.sortie_facile_backend.common.model.UserNameFormat;

import java.time.LocalDate;
import java.util.List;

public record ProfileDTO(
        Long id,
        UserNameFormat name,
        String streetNumber,
        String street,
        String zipCode,
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
