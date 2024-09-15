package com.poec.sortie_facile_backend.domain.region.dto;

import jakarta.validation.constraints.*;

public record SaveRegionDTO(
        @NotNull(message = "The name field is required.")
        @NotBlank(message = "The name field cannot be empty.")
        @Size(min = 2, max = 50, message = "The name field must contain between 2 and 50 characters.")
        String name
) {
}
