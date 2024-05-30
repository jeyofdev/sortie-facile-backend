package com.poec.projet_backend.domain.booking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poec.projet_backend.domain.activity.Activity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("bookings")
    @JoinColumn(name = "activity_id")
    private Activity activity;
}
