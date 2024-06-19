package com.poec.projet_backend.domain.category;

import com.poec.projet_backend.domain.activity.Activity;
import com.poec.projet_backend.domain.profile.Profile;
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

    @OneToMany(mappedBy = "category")
    private List<Activity> activities = new ArrayList<>();

    @Column(name = "img_url", columnDefinition = "VARCHAR(255)", nullable = false)
    private String imgUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Profile> profiles = new ArrayList<>();
}
