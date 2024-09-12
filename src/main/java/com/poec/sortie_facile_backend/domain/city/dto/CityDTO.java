package com.poec.sortie_facile_backend.domain.city.dto;

import java.util.List;

public record CityDTO(
        Long id,
        String name,
        List<Long> activityIds,
        Long departmentId
) {
}
