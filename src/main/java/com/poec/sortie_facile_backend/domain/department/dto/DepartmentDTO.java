package com.poec.sortie_facile_backend.domain.department.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DepartmentDTO(
        Long id,
        String name,
        String number,
        List<Long> activityIds,
        Long regionId,
        List<Long> cityIds,
        List<Long> profileIds
) {

}
