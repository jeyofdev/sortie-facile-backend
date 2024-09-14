package com.poec.sortie_facile_backend.domain.booking.dto;

import java.util.Date;

public record BookingDTO(
        Long id,
        Date createdAt,
        Long activityId,
        Long profileId
) {

}
