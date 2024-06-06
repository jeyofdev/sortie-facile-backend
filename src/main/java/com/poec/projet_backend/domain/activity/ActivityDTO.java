package com.poec.projet_backend.domain.activity;

import com.poec.projet_backend.domain.booking.Booking;
import com.poec.projet_backend.domain.category.Category;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public record ActivityDTO(
        Long id,
        String name,
        Date date,
        int age,
        String imgUrl,
        String link,
        String description,
        int nbGuest,
        LocalTime hour,
        boolean isVisible,
        String city,
        String department,
        String region,
        List<Long> bookingIds,
        List<Long> categoryIds
) {
    public static ActivityDTO mapFromEntity(Activity activity) {
        return new ActivityDTO(
            activity.getId(),
            activity.getName(),
            activity.getDate(),
            activity.getAge(),
            activity.getImgUrl(),
            activity.getLink(),
            activity.getDescription(),
            activity.getNbGuest(),
            activity.getHour(),
            activity.isVisible(),
            activity.getCity().getName(),
            activity.getDepartment().getName(),
            activity.getRegion().getName(),
            activity.getBookings().stream().map(Booking::getId).toList(),
            activity.getCategories().stream().map(Category::getId).toList()
        );
    }
}
