package com.poec.sortie_facile_backend.domain.activity;

import com.poec.sortie_facile_backend.domain.booking.Booking;
import com.poec.sortie_facile_backend.domain.category.Category;
import com.poec.sortie_facile_backend.domain.city.City;
import com.poec.sortie_facile_backend.domain.department.Department;
import com.poec.sortie_facile_backend.domain.profile.Profile;
import com.poec.sortie_facile_backend.domain.region.Region;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="name", columnDefinition = "VARCHAR(200)")
    @NotNull(message = "The name field is required.")
    @NotBlank(message = "The name field cannot be empty.")
    @Size(min = 5, max = 200, message = "The name field must contain between 5 and 200 characters.")
    private String name;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private Date createdAt;

    @Column(name="age", columnDefinition = "INT")
    @Min(value = 0, message ="The age must be at least 1.")
    @Max(value = 120, message ="The age cannot exceed 120 years.")
    private int age;

    @Column(name = "img_url", columnDefinition = "LONGTEXT")
    @Pattern(regexp = "^(https?|ftp)://[^\s/$.?#].[^\s]*$", message = "The image URL must be a valid URL.")
    private String imgUrl;

    @Column(name = "link", columnDefinition = "LONGTEXT")
    @Pattern(regexp = "^(https?|ftp)://[^\s/$.?#].[^\s]*$", message = "The link must be a valid URL.")
    private String link;

    @Column(name="description", columnDefinition = "LONGTEXT")
    @NotBlank(message = "The description is required.")
    private String description;

    @Column(name="nb_guest", columnDefinition = "INT")
    @Min(value = 1, message ="The nb guest must be at least 1.")
    @Max(value = 100, message ="The nb guest cannot exceed 100 years.")
    private int nbGuest;

//    @Column(name="hour", nullable = false)
//    private String hour;

    @Column(name="isVisible", columnDefinition = "BOOLEAN" , nullable = false )
    private boolean isVisible;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    private Region region;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;
}
