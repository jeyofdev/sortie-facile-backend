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

    @JsonProperty("streetNumber")
    private String streetNumber;

    @JsonProperty("street")
    private String street;

    @JsonProperty("zipCode")
    private String zipCode;

    @JsonProperty("description")
    private String description;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("dateOfBirth")
    private String dateOfBirth;

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
}
