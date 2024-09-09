package com.poec.sortie_facile_backend.domain.department;

import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.city.City;

import java.util.List;

public record DepartmentDTO(
        Long id,
        String name,
        Long regionId,
        List<Long> cityIds,
        List<Long> activityIds
) {
    public static DepartmentDTO mapFromEntity(Department department) {
        return new DepartmentDTO(
                department.getId(),
                department.getName(),
                department.getRegion().getId(),
                department.getCities().stream().map(City::getId).toList(),
                department.getActivities().stream().map(Activity::getId).toList()
        );
    }
}
