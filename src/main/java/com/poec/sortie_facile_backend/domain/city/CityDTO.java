package com.poec.sortie_facile_backend.domain.city;

import com.poec.sortie_facile_backend.domain.activity.Activity;

import java.util.List;

public record CityDTO(
        Long id,
        String name,
        Long departmentId,
        List<Long> activityIds
) {
    public static CityDTO mapFromEntity(City city) {
        return new CityDTO(
                city.getId(),
                city.getName(),
                city.getDepartment().getId(),
                city.getActivities().stream().map(Activity::getId).toList()
        );
    }
}
