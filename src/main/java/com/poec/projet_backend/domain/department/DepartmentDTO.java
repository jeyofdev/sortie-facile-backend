package com.poec.projet_backend.domain.department;

import com.poec.projet_backend.domain.activity.Activity;
import com.poec.projet_backend.domain.profile.Profile;

import java.util.List;

public record DepartmentDTO(
        Long id,
        String name,
        List<Long> activityIds,
        List<Long> profileIds
) {
    public static DepartmentDTO mapFromEntity(Department department) {
        return new DepartmentDTO(
                department.getId(),
                department.getName(),
                department.getActivities().stream().map(Activity::getId).toList(),
                department.getProfiles().stream().map(Profile::getId).toList()
        );
    }
}
