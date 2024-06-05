package com.poec.projet_backend.domain.department;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department")
    @JsonIgnoreProperties("department")
    private List<Activity> activities = new ArrayList<>();

    @OneToMany(mappedBy = "department")
    @JsonIgnoreProperties("department")
    private List<Profile> profiles = new ArrayList<>();
}
