package com.poec.sortie_facile_backend.domain.activity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.poec.sortie_facile_backend.common.model.AgeFormat;
import com.poec.sortie_facile_backend.common.model.ListRelationWithSizeFormat;
import com.poec.sortie_facile_backend.common.model.LocationFormat;
import com.poec.sortie_facile_backend.domain.category.dto.CategoryDTO;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ActivityDTO(
        Long id,
        String name,
        Date createdDate,
        AgeFormat age,
        String imgUrl,
        String link,
        String description,
        int nbGuest,
        boolean isVisible,
        LocationFormat location,
        ListRelationWithSizeFormat<CategoryDTO> categories,
        Long creatorUserId,
        ListRelationWithSizeFormat<Long> bookingIds
) {
}
