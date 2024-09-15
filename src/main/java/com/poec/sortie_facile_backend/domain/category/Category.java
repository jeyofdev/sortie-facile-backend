package com.poec.sortie_facile_backend.domain.category;

import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.profile.Profile;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", columnDefinition = "VARCHAR(200)")
    @NotNull(message = "The title field is required.")
    @NotBlank(message = "The title field cannot be empty.")
    @Size(min = 5, max = 200, message = "The title field must contain between 5 and 200 characters.")
    private String title;

    @Column(name = "img_url", columnDefinition = "LONGTEXT")
    @NotBlank(message = "The image URL field cannot be empty.")
    private String imgUrl;

    @ManyToMany(mappedBy = "categoryList", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Activity> activityList = new ArrayList<>();

    @ManyToMany(mappedBy = "categoryList", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Profile> profileList = new ArrayList<>();
}
