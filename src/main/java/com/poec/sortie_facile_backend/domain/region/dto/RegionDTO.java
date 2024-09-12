package com.poec.sortie_facile_backend.domain.region.dto;

import java.util.List;

public record RegionDTO(
        Long id,
        String name,
        List<Long> departmentIds,
        List<Long> activityIds
        /*List<Long> profileIds*/
) {

}
