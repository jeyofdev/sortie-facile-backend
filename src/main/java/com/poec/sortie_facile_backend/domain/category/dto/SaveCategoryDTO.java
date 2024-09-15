package com.poec.sortie_facile_backend.domain.category.dto;

import jakarta.validation.constraints.*;

public record SaveCategoryDTO(
        @NotNull(message = "The title field is required.")
        @NotBlank(message = "The title field cannot be empty.")
        @Size(min = 5, max = 200, message = "The title field must contain between 5 and 200 characters.")
        String title,

        @NotBlank(message = "The image URL field cannot be empty.")
        @Pattern(regexp = "^(https?|ftp)://[^\s/$.?#].[^\s]*$", message = "The image URL must be a valid URL.")
        String imgUrl
) {

}
