package com.poec.sortie_facile_backend.domain.contact.dto;

import jakarta.validation.constraints.*;

public record SaveContactDTO(
        @NotNull(message = "The title field is required.")
        @NotBlank(message = "The title field cannot be empty.")
        @Size(min = 5, max = 200, message = "The title field must contain between 5 and 200 characters.")
        String title,

        @Email(message = "The email format is incorrect. Please enter a valid email address.")
        @NotNull(message = "The email field is required.")
        @NotBlank(message = "The email field cannot be empty.")
        String email,

        @NotNull(message = "The message is required.")
        @NotBlank(message = "The message cannot be empty.")
        String message,

        boolean isRead
) {
}
