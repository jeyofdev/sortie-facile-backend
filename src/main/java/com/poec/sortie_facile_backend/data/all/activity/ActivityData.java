package com.poec.sortie_facile_backend.data.all.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ActivityData {
    @JsonProperty("name")
    private String name;

    @JsonProperty("createdAt")
    private String createdAt;

    @JsonProperty("ageMin")
    private int ageMin;

    @JsonProperty("ageMax")
    private int ageMax;

    @JsonProperty("imgUrl")
    private String imgUrl;

    @JsonProperty("link")
    private String link;

    @JsonProperty("description")
    private String description;

    @JsonProperty("nbGuest")
    private int nbGuest;

    @JsonProperty("isVisible")
    private Boolean isVisible;

    @JsonProperty("regionId")
    private Long regionId;

    @JsonProperty("departmentId")
    private Long departmentId;

    @JsonProperty("cityId")
    private Long cityId;

    @JsonProperty("categoryIds")
    private List<Long> categoryIds;

    @JsonProperty("creatorUserId")
    private Long creatorUserId;
}
