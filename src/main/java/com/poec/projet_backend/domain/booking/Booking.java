package com.poec.projet_backend.domain.booking;

import com.poec.projet_backend.domain.activity.Activity;
import com.poec.projet_backend.domain.profile.Profile;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "activity_id")
//    private Activity activity;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "profile_id")
//    private Profile profile;

    @OneToMany(mappedBy = "booking")
    private List<Activity> activities = new ArrayList<>();

    @OneToMany(mappedBy = "booking")
    private List<Profile> profiles = new ArrayList<>();

    public Booking(String createdAt) {
        this.createdAt = createdAt;
    }
}
