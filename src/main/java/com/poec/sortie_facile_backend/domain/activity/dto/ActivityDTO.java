package com.poec.sortie_facile_backend.domain.activity.dto;

import java.util.Date;
import java.util.List;

public record ActivityDTO(
        Long id,
        String name,
        Date createdDate,
        int age,
        String imgUrl,
        String link,
        String description,
        int nbGuest,
        boolean isVisible,
        Long regionId,
        Long departmentId,
        Long cityId,
        List<Long> categoryIds,
        Long profileId,
        List<Long> bookingIds
) {
}
