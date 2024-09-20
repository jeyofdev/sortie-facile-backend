package com.poec.sortie_facile_backend.domain.department.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.poec.sortie_facile_backend.common.model.ListIdsFormat;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DepartmentDTO(
        Long id,
        String name,
        String number,
        ListIdsFormat activityIds,
        Long regionId,
        ListIdsFormat cityIds,
        List<Long> profileIds
) {

}
