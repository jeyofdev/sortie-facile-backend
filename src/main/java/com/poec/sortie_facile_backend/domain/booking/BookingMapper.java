package com.poec.sortie_facile_backend.domain.booking;

import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.booking.dto.BookingDTO;
import com.poec.sortie_facile_backend.domain.profile.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingMapper {

    public BookingDTO mapFromEntity(Booking booking) {
        return new BookingDTO(
                booking.getId(),
                booking.getCreatedAt(),
                Optional.ofNullable(booking.getActivity()).map(Activity::getId).orElse(null),
                Optional.ofNullable(booking.getProfile()).map(Profile::getId).orElse(null)
        );
    }
}
