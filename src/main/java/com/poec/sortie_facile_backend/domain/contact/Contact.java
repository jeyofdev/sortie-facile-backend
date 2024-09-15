package com.poec.sortie_facile_backend.domain.contact;

import jakarta.persistence.*;
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
    private String title;

    @Column(name = "email", columnDefinition = "VARCHAR(100)")
    private String email;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Column(name = "isRead", columnDefinition = "BOOLEAN", nullable = false)
    private boolean isRead;
}
