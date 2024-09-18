package com.poec.sortie_facile_backend.data.all.activity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ActivityDataInfo {
    private String name;
    private Date createdAt;
    private int age;
    private String imgUrl;
    private String link;
    private String description;
    private int nbGuest;
    private boolean isVisible;
    Long regionId;
    Long departmentId;
    Long cityId;
    List<Long> categoryIds;
    private Long profileId;
}
