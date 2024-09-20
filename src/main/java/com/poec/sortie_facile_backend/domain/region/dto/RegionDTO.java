package com.poec.sortie_facile_backend.domain.region.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.poec.sortie_facile_backend.common.model.ListIdsFormat;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RegionDTO(
        Long id,
        String name,
        ListIdsFormat departmentIds,
        ListIdsFormat activityIds,
        List<Long> profileIds
) {

}
