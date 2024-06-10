package com.poec.projet_backend.domain.activity;

import com.poec.projet_backend.domain.booking.Booking;
import com.poec.projet_backend.domain.city.City;
import com.poec.projet_backend.domain.department.Department;
import com.poec.projet_backend.domain.region.Region;

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
//        City city,
//        Department department,
//        Region region,
        List<Long> bookings,
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
