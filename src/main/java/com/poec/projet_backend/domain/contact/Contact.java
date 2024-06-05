package com.poec.projet_backend.domain.contact;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", columnDefinition = "VARCHAR(100)", nullable = false)
    private String title;

    @Column(name = "email", columnDefinition = "VARCHAR(100)", nullable = false)
    private String email;

    @Column(name = "message", columnDefinition = "TEXT", nullable = false)
    private String message;
}
