package com.poec.projet_backend.domain.activity;

import com.poec.projet_backend.domain.booking.Booking;
import com.poec.projet_backend.domain.category.Category;
import com.poec.projet_backend.domain.city.City;
import com.poec.projet_backend.domain.department.Department;
import com.poec.projet_backend.domain.profile.Profile;
import com.poec.projet_backend.domain.region.Region;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", columnDefinition = "VARCHAR(100)", nullable = false)
    private String name;

    @Column(name="date", nullable = false)
    private Date date;

    @Column(name="age", columnDefinition = "INT")
    private int age;

    @Column(name="imgUrl", columnDefinition =  "VARCHAR(255)")
    private String imgUrl;

    @Column(name="link", columnDefinition = "VARCHAR(255)")
    private String link;

    @Column(name="description", columnDefinition = "TEXT")
    private String description;

    @Column(name="nb_guest", columnDefinition = "INT")
    private int nbGuest;

//    @Column(name="hour", nullable = false)
//    private String hour;

    @Column(name="isVisible", columnDefinition = "BOOLEAN" ,nullable = false )
    private boolean isVisible;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "activity")
    private List<Booking> bookings = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public Activity(String name, Date date, int age, String imgUrl, String link, String description, int nbGuest, boolean isVisible) {
        this.name = name;
        this.date = date;
        this.age = age;
        this.imgUrl = imgUrl;
        this.link = link;
        this.description = description;
        this.nbGuest = nbGuest;
        this.isVisible = isVisible;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", age=" + age +
                ", imgUrl='" + imgUrl + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", nbGuest=" + nbGuest +
                ", isVisible=" + isVisible +
                ", city=" + city +
                ", region=" + region +
                ", department=" + department +
                ", bookings=" + bookings +
                ", category=" + category +
                ", profile=" + profile +
                '}';
    }


}
