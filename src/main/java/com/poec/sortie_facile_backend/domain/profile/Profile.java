package com.poec.sortie_facile_backend.domain.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.booking.Booking;
import com.poec.sortie_facile_backend.domain.category.Category;
import com.poec.sortie_facile_backend.domain.city.City;
import com.poec.sortie_facile_backend.domain.department.Department;
import com.poec.sortie_facile_backend.domain.region.Region;
import com.poec.sortie_facile_backend.auth_user.AuthUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", columnDefinition = "VARCHAR(100)", nullable = false)
    private String firstname;

    @Column(name = "lastname", columnDefinition = "VARCHAR(100)", nullable = false)
    private String lastname;

    @Column(name = "street_number", columnDefinition = "VARCHAR(100)")
    private String streetNumber;

    @Column(name = "street", columnDefinition = "VARCHAR(100)")
    private String street;

    @Column(name = "postal_code", columnDefinition = "INT(5)", nullable = false)
    private Long postalCode;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "avatar", columnDefinition = "VARCHAR(255)")
    private String avatar;

    @Column(name = "phone", columnDefinition = "VARCHAR(14)")
    private String phone;

    @Column(name = "date_of_birth", nullable = false)
    private String dateOfBirth;

    @OneToMany(mappedBy = "profile", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Activity> activities;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    private Region region;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    /*@ManyToMany(cascade = CascadeType.PERSIST)
    private List<Category> categories = new ArrayList<>();*/

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties("profile")
    private AuthUser user;
}






