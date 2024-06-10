package com.poec.projet_backend.domain.city;

import com.poec.projet_backend.domain.activity.Activity;
import com.poec.projet_backend.domain.profile.Profile;

import java.util.List;

public record CityDTO(
        Long id,
        String name,
        Long departmentId,
        List<Long> activityIds
//        List<Long> profileIds
) {
    public static CityDTO mapFromEntity(City city) {
        return new CityDTO(
                city.getId(),
                city.getName(),
                city.getDepartment().getId(),
                city.getActivities().stream().map(Activity::getId).toList()
//                city.getProfiles().stream().map(Profile::getId).toList()
        );
    }
}
