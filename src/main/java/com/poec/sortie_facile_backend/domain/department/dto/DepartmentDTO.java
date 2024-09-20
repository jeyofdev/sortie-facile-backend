package com.poec.sortie_facile_backend.domain.department.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.poec.sortie_facile_backend.common.model.ListRelationWithSizeFormat;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DepartmentDTO(
        Long id,
        String name,
        String number,
        ListRelationWithSizeFormat<Long> activityIds,
        Long regionId,
        ListRelationWithSizeFormat<Long> cityIds,
        ListRelationWithSizeFormat<Long> profileIds
) {

}
