package com.poec.sortie_facile_backend.domain.activity.dto;

public record SaveActivityDTO(
        String name,
        int age,
        String imgUrl,
        String link,
        String description,
        int nbGuest,
        boolean isVisible
) {
}
