package com.poec.sortie_facile_backend.domain.profile.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record SaveProfileDTO(
        @NotNull(message = "The firstname field is required.")
        @NotBlank(message = "The name firstname cannot be empty.")
        @Size(min = 2, max = 50, message = "The firstname field must contain between 2 and 50 characters.")
        String firstname,

        @NotNull(message = "The lastname field is required.")
        @NotBlank(message = "The name lastname cannot be empty.")
        @Size(min = 2, max = 50, message = "The lastname field must contain between 2 and 50 characters.")
        String lastname,

        @NotNull(message = "The street number field is required.")
        @NotBlank(message = "The name street number cannot be empty.")
        @Pattern(regexp = "^[0-9]*$", message = "The street number must be a maximum of 4 digits.")
        String streetNumber,

        @NotNull(message = "The street field is required.")
        @NotBlank(message = "The name street cannot be empty.")
        @Size(min = 2, max = 50, message = "The street field must contain between 2 and 50 characters.")
        String street,

        @NotNull(message = "The zip code field is required.")
        @Pattern(regexp = "\\d{5}", message = "The zip code must be exactly 5 digits.")
        String zipCode,

        @NotNull(message = "The description field is required.")
        @NotBlank(message = "The description field cannot be empty.")
        String description,

        @Pattern(regexp = "^(https?|ftp)://[^\s/$.?#].[^\s]*$", message = "The avatar URL must be a valid URL.")
        String avatar,

        @Pattern(regexp = "^\\+?[0-9]*$", message = "Please provide a valid phone number.")
        String phone,

        @NotNull(message = "The date of birth field is required.")
        LocalDate dateOfBirth,

        @Nullable List<Long> categoryIds,
        @Nullable Long regionId,
        @Nullable Long departmentId,
        @Nullable Long cityId
) {

}
