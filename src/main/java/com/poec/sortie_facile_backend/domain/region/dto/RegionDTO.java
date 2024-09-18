package com.poec.sortie_facile_backend.domain.region.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RegionDTO(
        Long id,
        String name,
        List<Long> departmentIds,
        List<Long> activityIds,
        List<Long> profileIds
) {

}
