package com.poec.sortie_facile_backend.domain.city.dto;

import jakarta.annotation.Nullable;

public record SaveCityDTO(
        String name,
        String postalCode,
        @Nullable Long departmentId
) {

}
