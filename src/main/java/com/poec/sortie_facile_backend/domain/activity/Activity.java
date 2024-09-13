package com.poec.sortie_facile_backend.domain.activity;

import com.poec.sortie_facile_backend.domain.booking.Booking;
import com.poec.sortie_facile_backend.domain.category.Category;
import com.poec.sortie_facile_backend.domain.city.City;
import com.poec.sortie_facile_backend.domain.department.Department;
import com.poec.sortie_facile_backend.domain.profile.Profile;
import com.poec.sortie_facile_backend.domain.region.Region;
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

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    private Region region;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    /*@OneToMany(mappedBy = "activity")
    private List<Booking> bookings = new ArrayList<>();*/

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
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
                /*", bookings=" + bookings +*/
                ", category=" + category +
                ", profile=" + profile +
                '}';
    }


}
