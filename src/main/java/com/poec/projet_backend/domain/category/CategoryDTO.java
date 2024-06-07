package com.poec.projet_backend.domain.category;

import com.poec.projet_backend.domain.activity.Activity;
import com.poec.projet_backend.domain.profile.Profile;

import java.util.List;

public record CategoryDTO(
        String title
//        List<Long> activityIds,
//        List<Long> profileIds
) {
    public static CategoryDTO mapFromEntity(Category category) {
        return new CategoryDTO(
                category.getTitle()
//                category.getActivities().stream().map(Activity::getId).toList(),
//                category.getProfiles().stream().map(Profile::getId).toList()
        );
    }
}
