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

    @Column(name = "firstname", columnDefinition = "VARCHAR(100)", nullable = false)
    private String firstname;

    @Column(name = "lastname", columnDefinition = "VARCHAR(100)", nullable = false)
    private String lastname;

    @Column(name = "streetNumber", columnDefinition = "VARCHAR(100)")
    private String streetNumber;

    @Column(name = "street", columnDefinition = "VARCHAR(100)")
    private String street;

    @Column(name = "zip_code", columnDefinition = "INT(5)", nullable = false)
    private int postalCode;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "avatar", columnDefinition = "VARCHAR(255)")
    private String avatar;

    @Column(name = "phone", columnDefinition = "VARCHAR(14)")
    private String phone;

    @Column(name = "dateOfBirth", nullable = false)
    private String dateOfBirth;

    @ManyToMany(mappedBy = "profiles")
    private List<Category> categories = new ArrayList<>();

    @ManyToMany
    private List<Booking> bookings = new ArrayList<>();

    @OneToOne(mappedBy = "profile")
    private UserApp user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "region_id")
    private Region region;

    public Profile(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Profile(String firstname, String lastname, String streetNumber, String street, int postalCode, String description, String avatar, String phone, String dateOfBirth) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.streetNumber = streetNumber;
        this.street = street;
        this.postalCode = postalCode;
        this.description = description;
        this.avatar = avatar;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }
}






