package com.poec.sortie_facile_backend.domain.activity.dto;

import jakarta.annotation.Nullable;

import java.util.List;

public record SaveActivityDTO(
        String name,
        int age,
        String imgUrl,
        String link,
        String description,
        int nbGuest,
        boolean isVisible,
        @Nullable List<Long> categoryIds,
        @Nullable Long regionId,
        @Nullable Long departmentId,
        @Nullable Long cityId
) {
}
