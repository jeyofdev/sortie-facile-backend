package com.poec.sortie_facile_backend.domain.category;

import com.poec.sortie_facile_backend.common.model.ListIdsFormat;
import com.poec.sortie_facile_backend.core.interfaces.BaseDomainMapper;
import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.category.dto.CategoryDTO;
import com.poec.sortie_facile_backend.domain.category.dto.SaveCategoryDTO;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper implements BaseDomainMapper<Category, CategoryDTO, SaveCategoryDTO> {
    @Override
    public CategoryDTO mapFromEntity(Category category, boolean primaryDataOnly, boolean isAdmin) {
        return new CategoryDTO(
                category.getId(),
                category.getTitle(),
                !primaryDataOnly ? category.getImgUrl() : null,
                !primaryDataOnly ? new ListIdsFormat(
                        category.getActivityList().size(),
                        category.getActivityList().stream().map(Activity::getId).toList()
                ) : null
        );
    }

    @Override
    public Category mapToEntity(SaveCategoryDTO saveCategoryDTO) {
        Category category = new Category();
        category.setTitle(saveCategoryDTO.title());
        category.setImgUrl(saveCategoryDTO.imgUrl());

        return category;
    }
}
