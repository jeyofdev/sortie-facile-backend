package com.poec.projet_backend.domain.profile;

import com.poec.projet_backend.domain.category.Category;

import java.util.List;

public record ProfileUpdateCategoriesDTO(
        List<Long> categoryIds
) {
    public static ProfileUpdateCategoriesDTO mapFromEntity(Profile profile) {
        return new ProfileUpdateCategoriesDTO (
                profile.getCategories().stream().map(Category::getId).toList()
        );
    }
}
