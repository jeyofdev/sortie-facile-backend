package com.poec.projet_backend.domain.booking;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public record BookingFrontToBackDTO(
        String createdAt,
        Optional<Long> activityId,
        List<Long> profileIds
) {
    public static Booking mapToEntity(BookingFrontToBackDTO bookingFrontToBackDTO) {
        return new Booking(
                bookingFrontToBackDTO.createdAt()
        );
    }
}
