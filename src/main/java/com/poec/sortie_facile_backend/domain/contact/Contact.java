package com.poec.sortie_facile_backend.domain.contact;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", columnDefinition = "VARCHAR(200)")
    @NotNull(message = "The title field is required.")
    @NotBlank(message = "The title field cannot be empty.")
    @Size(min = 5, max = 200, message = "The title field must contain between 5 and 200 characters.")
    private String title;

    @Column(name = "email", columnDefinition = "VARCHAR(100)")
    @Email(message = "The email format is incorrect. Please enter a valid email address.")
    @NotNull(message = "The email field is required.")
    @NotBlank(message = "The email field cannot be empty.")
    private String email;

    @Column(name = "message", columnDefinition = "TEXT")
    @NotNull(message = "The message is required.")
    @NotBlank(message = "The message cannot be empty.")
    private String message;

    @Column(name = "isRead", columnDefinition = "BOOLEAN", nullable = false)
    private boolean isRead;
}
