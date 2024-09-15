package com.poec.sortie_facile_backend.domain.activity.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;

import java.util.List;

public record SaveActivityDTO(
        @NotNull(message = "The name field is required.")
        @NotBlank(message = "The name field cannot be empty.")
        @Size(min = 5, max = 200, message = "The name field must contain between 5 and 200 characters.")
        String name,

        @Min(value = 0, message ="The age must be at least 1.")
        @Max(value = 120, message ="The age cannot exceed 120 years.")
        int age,

        @Pattern(regexp = "^(https?|ftp)://[^\s/$.?#].[^\s]*$", message = "The image URL must be a valid URL.")
        String imgUrl,

        @Pattern(regexp = "^(https?|ftp)://[^\s/$.?#].[^\s]*$", message = "The link must be a valid URL.")
        String link,

        @NotBlank(message = "The description is required.")
        String description,

        @Min(value = 1, message ="The nb guest must be at least 1.")
        @Max(value = 100, message ="The nb guest cannot exceed 100 years.")
        int nbGuest,

        boolean isVisible,
        @Nullable List<Long> categoryIds,
        @Nullable Long regionId,
        @Nullable Long departmentId,
        @Nullable Long cityId
) {
}
