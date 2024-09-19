package com.poec.sortie_facile_backend.domain.category.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CategoryDTO(
        Long id,
        String title,
        String imgUrl,
        List<Long> activityIds
) {

}
