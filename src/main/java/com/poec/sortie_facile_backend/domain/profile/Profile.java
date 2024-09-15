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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname", columnDefinition = "VARCHAR(50)")
    private String firstname;

    @Column(name = "lastname", columnDefinition = "VARCHAR(50)")
    private String lastname;

    @Column(name = "street_number", columnDefinition = "VARCHAR(4)")
    private String streetNumber;

    @Column(name = "street", columnDefinition = "VARCHAR(50)")
    private String street;

    @Column(name = "postalCode", columnDefinition = "VARCHAR(5)")
    private String postalCode;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "avatar", columnDefinition = "LONGTEXT")
    private String avatar;

    @Column(name = "phone", columnDefinition = "VARCHAR(10)")
    private String phone;

    @Column(name = "date_of_birth", columnDefinition = "Date")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "profile", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Activity> activityList;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    private Region region;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "profile_category",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categoryList = new ArrayList<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Booking> bookingList = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties("profile")
    private AuthUser user;
}






