package com.poec.sortie_facile_backend.domain.department.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;

public record SaveDepartmentDTO(
        @NotNull(message = "The name field is required.")
        @NotBlank(message = "The name field cannot be empty.")
        @Size(min = 2, max = 50, message = "The name field must contain between 2 and 50 characters.")
        String name,

        @NotNull(message = "The department number field is required.")
        @Pattern(regexp = "\\d{2,3}", message = "The department number must be composed of 2 or 3 digits.")
        String number,

        @Nullable Long regionId
) {

}
