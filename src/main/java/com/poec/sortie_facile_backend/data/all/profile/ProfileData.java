package com.poec.sortie_facile_backend.data.all.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProfileData {
    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("dateOfBirth")
    private String dateOfBirth;

    @JsonProperty("streetNumber")
    private String streetNumber;

    @JsonProperty("street")
    private String street;

    @JsonProperty("zipCode")
    private String zipCode;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("twitter")
    private String twitter;

    @JsonProperty("instagram")
    private String instagram;

    @JsonProperty("facebook")
    private String facebook;

    @JsonProperty("description")
    private String description;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("regionId")
    private Long regionId;

    @JsonProperty("departmentId")
    private Long departmentId;

    @JsonProperty("cityId")
    private Long cityId;

    @JsonProperty("activityIds")
    private List<Long> activityIds;

    @JsonProperty("categoryIds")
    private List<Long> categoryIds;

    @JsonProperty("bookingIds")
    private List<Long> bookingIds;

    @JsonProperty("profileId")
    private Long profileId;
}
