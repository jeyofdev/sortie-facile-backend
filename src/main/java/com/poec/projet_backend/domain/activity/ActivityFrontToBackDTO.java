package com.poec.projet_backend.domain.activity;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public record ActivityFrontToBackDTO(
        String name,
        String date,
        int age,
        String imgUrl,
        String link,
        String description,
        int nbGuest,
        String hour,
        boolean isVisible,
        Optional<Long> cityId,
        Optional<Long> departmentId,
        Optional<Long> regionId,
        List<Long> bookingIds,
        List<Long> categoryIds
) {
    public static Activity mapToEntity(ActivityFrontToBackDTO activityDTO) {
        return new Activity(
                activityDTO.name(),
                activityDTO.date(),
                activityDTO.age(),
                activityDTO.imgUrl(),
                activityDTO.link(),
                activityDTO.description(),
                activityDTO.nbGuest(),
                activityDTO.hour(),
                activityDTO.isVisible()
        );
    }
}
