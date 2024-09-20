package com.poec.sortie_facile_backend.domain.city.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.poec.sortie_facile_backend.common.model.ListRelationWithSizeFormat;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CityDTO(
        Long id,
        String name,
        String zipCode,
        ListRelationWithSizeFormat<Long> activityIds,
        Long departmentId,
        ListRelationWithSizeFormat<Long> profileIds
) {
}
