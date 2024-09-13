package com.poec.sortie_facile_backend.domain.booking.dto;

public record BookingDTO(
        Long id,
        String createdAt,
        Long activityId,
        Long profileId
) {

}
