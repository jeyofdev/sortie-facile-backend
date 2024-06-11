package com.poec.projet_backend.domain.profile;

public record ProfileDTO(
        Long id,
        String firstname,
        String lastname,
        String streetNumber,
        String street,
        int postalCode,
        String description,
        String avatar,
        String phone,
        String dateOfBirth,
//        List<Long> categoryIds,
//        List<Long> bookingIds,
//        Long userId
        Long cityId,
        Long departmentId,
        Long regionId
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
//                profile.getCategories().stream().map(Category::getId).toList(),
//                profile.getBookings().stream().map(Booking::getId).toList(),
//                profile.getUser().getId()
                profile.getCity().getId(),
                profile.getDepartment().getId(),
                profile.getRegion().getId()
        );
    }
}
