package com.poec.sortie_facile_backend.domain.department.dto;

import java.util.List;

public record DepartmentDTO(
        Long id,
        String name,
        Long regionId,
        List<Long> cityIds,
        List<Long> activityIds
) {

}
