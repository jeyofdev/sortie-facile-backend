package com.poec.projet_backend.domain.booking;

public record BookingDTO(
        Long id,
        String createdAt,
        Long activityId,
        Long profileId
) {
    public static BookingDTO mapFromEntity(Booking booking) {
        return new BookingDTO(
                booking.getId(),
                booking.getCreatedAt(),
                booking.getActivity().getId(),
                booking.getProfile().getId()
        );
    }
}
