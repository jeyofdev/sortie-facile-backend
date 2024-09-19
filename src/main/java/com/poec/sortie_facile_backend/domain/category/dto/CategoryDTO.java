package com.poec.sortie_facile_backend.domain.category.dto;

import java.util.List;

public record CategoryDTO(
        Long id,
        String title,
        String imgUrl,
        List<Long> activityIds
) {

}
