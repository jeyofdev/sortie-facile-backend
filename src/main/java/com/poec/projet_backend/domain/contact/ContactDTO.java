package com.poec.projet_backend.domain.contact;

public record ContactDTO(
        Long id,
        String title,
        String email,
        String message
) {
    public static ContactDTO mapFromEntity (Contact contact) {
        return new ContactDTO(
                contact.getId(),
                contact.getTitle(),
                contact.getEmail(),
                contact.getMessage()
        );
    }
}
