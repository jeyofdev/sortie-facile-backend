package com.poec.sortie_facile_backend.domain.contact.dto;

public record ContactDTO(
        Long id,
        String title,
        String email,
        String message,
        boolean isRead
) {
}
