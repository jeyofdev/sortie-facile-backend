package com.poec.sortie_facile_backend.domain.category;

import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.profile.Profile;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", columnDefinition = "VARCHAR(100)", nullable = false)
    private String title;

    @Column(name = "img_url", columnDefinition = "VARCHAR(255)", nullable = false)
    private String imgUrl;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Activity> activities = new ArrayList<>();

    /*@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @ManyToMany(mappedBy = "categories")
    private List<Profile> profiles = new ArrayList<>();*/

    public Category(String title, String imgUrl) {
        this.title = title;
        this.imgUrl = imgUrl;
    }
}
