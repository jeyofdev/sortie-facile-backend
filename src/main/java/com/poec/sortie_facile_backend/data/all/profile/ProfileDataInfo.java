package com.poec.sortie_facile_backend.data.all.profile;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class ProfileDataInfo {
    private String firstname;
    private String lastname;
    private String streetNumber;
    private String street;
    private String zipCode;
    private String description;
    private String avatar;
    private String phone;
    private LocalDate dateOfBirth;
    private Long regionId;
    private Long departmentId;
    private Long cityId;
    private List<Long> categoryIds;
    private List<Long> activityIds;
    private List<Long> bookingIds;
}
