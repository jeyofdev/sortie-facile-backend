package com.poec.projet_backend.domain.contact;

public record ContactDTO(
        Long id,
        String title,
        String email,
        String message,
        boolean isRead
) {
    public static ContactDTO mapFromEntity(Contact contact) {
        return new ContactDTO(
                contact.getId(),
                contact.getTitle(),
                contact.getEmail(),
                contact.getMessage(),
                contact.isRead()
        );
    }

    public static Contact mapToEntity(ContactDTO contactDTO) {
        return new Contact(
                contactDTO.title(),
                contactDTO.email(),
                contactDTO.message(),
                contactDTO.isRead()
        );
    }
}
