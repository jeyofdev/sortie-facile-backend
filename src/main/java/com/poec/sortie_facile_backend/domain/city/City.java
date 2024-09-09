package com.poec.projet_backend.domain.city;

import com.poec.projet_backend.domain.activity.Activity;
import com.poec.projet_backend.domain.department.Department;
import com.poec.projet_backend.domain.profile.Profile;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(100)", nullable = false)
    private String name;

    @Column(name = "postalCode", columnDefinition = "VARCHAR(100)", nullable = false)
    private String postalCode;

    @OneToMany(mappedBy = "city")
    private List<Activity> activities = new ArrayList<>();

    @OneToMany(mappedBy = "city")
    private List<Profile> profiles = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;

    public City(String name) {
        this.name = name;
    }
}
