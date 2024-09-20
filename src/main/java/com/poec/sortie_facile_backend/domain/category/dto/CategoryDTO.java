package com.poec.sortie_facile_backend.domain.category.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.poec.sortie_facile_backend.common.model.ListRelationWithSizeFormat;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CategoryDTO(
        Long id,
        String title,
        String imgUrl,
        ListRelationWithSizeFormat<Long> activityIds
) {

}
