package com.poec.projet_backend.domain.activity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poec.projet_backend.domain.booking.Booking;
import com.poec.projet_backend.domain.category.Category;
import com.poec.projet_backend.domain.city.City;
import com.poec.projet_backend.domain.department.Department;
import com.poec.projet_backend.domain.region.Region;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Date date;
    private int age;
    private String imgUrl;
    private String link;
    private String description;
    private int nbGuest;
    private LocalTime hour;
    private boolean isVisible;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("activities")
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("activities")
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("activities")
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "activity")
    @JsonIgnoreProperties("activity")
    private List<Booking> bookings = new ArrayList<>();

    @ManyToMany(mappedBy = "activities")
    @JsonIgnoreProperties("activities")
    private List<Category> categories= new ArrayList<>();
}
