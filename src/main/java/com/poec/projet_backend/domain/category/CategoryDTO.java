package com.poec.projet_backend.domain.category;

public record CategoryDTO(
        Long id,
        String title
) {
    public static CategoryDTO mapFromEntity(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getTitle()
        );
    }
}
