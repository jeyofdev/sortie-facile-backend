package com.poec.projet_backend.domain.booking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poec.projet_backend.domain.activity.Activity;
import com.poec.projet_backend.domain.profile.Profile;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @ManyToMany(mappedBy = "bookings")
    @JsonIgnoreProperties("bookings")
    private List<Profile> profiles = new ArrayList<>();
}
