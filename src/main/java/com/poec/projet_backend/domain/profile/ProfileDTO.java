package com.poec.projet_backend.domain.profile;

import com.poec.projet_backend.domain.booking.Booking;
import com.poec.projet_backend.domain.category.Category;

import java.util.List;

public record ProfileDTO(
        Long id,
        String firstname,
        String lastname,
        String streetNumber,
        String street,
        Long postalCode,
        String description,
        String avatar,
        String phone,
        String dateOfBirth,
        Long cityId,
        Long departmentId,
        Long regionId,
        List<Long> bookingIds,
        List<Long> categoryIds
) {
    public static ProfileDTO mapFromEntity(Profile profile) {
        return new ProfileDTO(
                profile.getId(),
                profile.getFirstname(),
                profile.getLastname(),
                profile.getStreetNumber(),
                profile.getStreet(),
                profile.getPostalCode(),
                profile.getDescription(),
                profile.getAvatar(),
                profile.getPhone(),
                profile.getDateOfBirth(),
                profile.getCity().getId(),
                profile.getDepartment().getId(),
                profile.getRegion().getId(),
                profile.getBookings().stream().map(Booking::getId).toList(),
                profile.getCategories().stream().map(Category::getId).toList()
        );
    }
}
