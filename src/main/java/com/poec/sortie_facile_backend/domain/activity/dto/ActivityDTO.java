package com.poec.sortie_facile_backend.domain.activity.dto;

import java.util.Date;

public record ActivityDTO(
        Long id,
        String name,
        Date date,
        int age,
        String imgUrl,
        String link,
        String description,
        int nbGuest,
        boolean isVisible,
        Long regionId,
        Long departmentId,
        Long cityId,
        Long  categoryId,
        Long profileId
) {
}
