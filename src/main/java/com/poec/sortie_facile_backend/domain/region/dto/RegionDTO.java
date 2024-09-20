package com.poec.sortie_facile_backend.domain.region.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.poec.sortie_facile_backend.common.model.ListRelationWithSizeFormat;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RegionDTO(
        Long id,
        String name,
        ListRelationWithSizeFormat<Long> departmentIds,
        ListRelationWithSizeFormat<Long> activityIds,
        ListRelationWithSizeFormat<Long> profileIds
) {

}
