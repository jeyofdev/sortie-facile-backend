package com.poec.projet_backend.domain.region;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String region;

//    @OneToMany(mappedBy = "region")
//    @JsonIgnoreProperties("region")
//    private List<Activity> activities = new ArrayList<>();
}
