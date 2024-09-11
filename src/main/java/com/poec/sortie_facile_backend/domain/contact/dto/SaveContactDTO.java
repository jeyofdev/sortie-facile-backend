package com.poec.sortie_facile_backend.domain.contact.dto;

public record SaveContactDTO(
        String title,
        String email,
        String message,
        boolean isRead
) {
}
