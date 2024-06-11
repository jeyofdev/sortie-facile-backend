package com.poec.projet_backend.domain.activity;

import com.poec.projet_backend.domain.booking.Booking;
import com.poec.projet_backend.domain.category.Category;

import java.util.List;

public record ActivityDTO(
        Long id,
        String name,
        String date,
        int age,
        String imgUrl,
        String link,
        String description,
        int nbGuest,
        String hour,
        boolean isVisible,
        Long cityId,
        Long departmentId,
        Long regionId
//        List<Long> bookingIds,
//        List<Long> categoryIds
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
            activity.getCity().getId(),
            activity.getDepartment().getId(),
            activity.getRegion().getId()
//            activity.getBookings().stream().map(Booking::getId).toList(),
//            activity.getCategories().stream().map(Category::getId).toList()
        );
    }
}
