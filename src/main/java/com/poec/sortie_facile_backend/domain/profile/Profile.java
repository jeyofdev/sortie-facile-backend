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
import jakarta.validation.constraints.*;
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
    @NotNull(message = "The firstname field is required.")
    @NotBlank(message = "The name firstname cannot be empty.")
    @Size(min = 2, max = 50, message = "The firstname field must contain between 2 and 50 characters.")
    private String firstname;

    @Column(name = "lastname", columnDefinition = "VARCHAR(50)")
    @NotNull(message = "The lastname field is required.")
    @NotBlank(message = "The name lastname cannot be empty.")
    @Size(min = 2, max = 50, message = "The lastname field must contain between 2 and 50 characters.")
    private String lastname;

    @Column(name = "street_number", columnDefinition = "VARCHAR(4)")
    @NotNull(message = "The street number field is required.")
    @NotBlank(message = "The name street number cannot be empty.")
    @Pattern(regexp = "^[0-9]*$", message = "The street number must be a maximum of 4 digits.")
    private String streetNumber;

    @Column(name = "street", columnDefinition = "VARCHAR(50)")
    @NotNull(message = "The street field is required.")
    @NotBlank(message = "The name street cannot be empty.")
    @Size(min = 2, max = 50, message = "The street field must contain between 2 and 50 characters.")
    private String street;

    @Column(name = "postalCode", columnDefinition = "VARCHAR(5)")
    @NotNull(message = "The postal code field is required.")
    @Pattern(regexp = "\\d{5}", message = "The postal code must be exactly 5 digits.")
    private String postalCode;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    @NotNull(message = "The description field is required.")
    @NotBlank(message = "The description field cannot be empty.")
    private String description;

    @Column(name = "avatar", columnDefinition = "LONGTEXT")
    @Pattern(regexp = "^(https?|ftp)://[^\s/$.?#].[^\s]*$", message = "The avatar URL must be a valid URL.")
    private String avatar;

    @Column(name = "phone", columnDefinition = "VARCHAR(10)")
    @Pattern(regexp = "^\\+?[0-9]*$", message = "Please provide a valid phone number.")
    private String phone;

    @Column(name = "date_of_birth", columnDefinition = "Date")
    @NotNull(message = "The date of birth field is required.")
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






