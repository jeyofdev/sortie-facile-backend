package com.poec.projet_backend.domain.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poec.projet_backend.domain.booking.Booking;
import com.poec.projet_backend.domain.category.Category;
import com.poec.projet_backend.domain.city.City;
import com.poec.projet_backend.domain.department.Department;
import com.poec.projet_backend.domain.region.Region;
import com.poec.projet_backend.user_app.UserApp;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String streetNumber;
    private String street;
    private int postalCode;
    private String description;
    private String avatar;
    private String phone;
    private Date dateOfBirth;
    // private String genre;

    @ManyToMany(mappedBy = "profiles")
    @JsonIgnoreProperties("profiles")
    private List<Category> categories = new ArrayList<>();

    @ManyToMany
    @JsonIgnoreProperties("profiles")
    private List<Booking> bookings = new ArrayList<>();

    @OneToOne(mappedBy = "profile")
    @JsonIgnoreProperties("profile")
    private UserApp user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("profiles")
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("profiles")
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("profiles")
    @JoinColumn(name = "region_id")
    private Region region;
}
