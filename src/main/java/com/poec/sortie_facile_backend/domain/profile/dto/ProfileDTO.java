package com.poec.sortie_facile_backend.domain.profile.dto;

import com.poec.sortie_facile_backend.common.model.UserNameFormat;
import com.poec.sortie_facile_backend.common.model.UserYearFormat;

import java.util.List;

public record ProfileDTO(
        Long id,
        UserNameFormat name,
        UserYearFormat year,
        String phone,
        String streetNumber,
        String street,
        String zipCode,
        String description,
        String avatar,
        List<Long> activityIds,
        Long regionId,
        Long departmentId,
        Long cityId,
        Long userId,
        List<Long> bookingIds,
        List<Long> categoryIds
) {

}
