package com.poec.sortie_facile_backend.domain.department.dto;

import jakarta.annotation.Nullable;

public record SaveDepartmentDTO(
        Long id,
        String name,
        @Nullable Long regionId
) {

}
