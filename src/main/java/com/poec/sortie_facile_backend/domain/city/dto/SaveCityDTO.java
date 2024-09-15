package com.poec.sortie_facile_backend.domain.city.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;

public record SaveCityDTO(
        @NotNull(message = "The name field is required.")
        @NotBlank(message = "The name field cannot be empty.")
        @Size(min = 2, max = 50, message = "The name field must contain between 2 and 50 characters.")
        String name,

        @NotNull(message = "The postal code field is required.")
        @Pattern(regexp = "\\d{5}", message = "The postal code must be exactly 5 digits.")
        String postalCode,

        @Nullable Long departmentId
) {

}
