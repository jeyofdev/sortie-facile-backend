package com.poec.sortie_facile_backend.domain.booking;

import com.poec.sortie_facile_backend.domain.booking.dto.BookingDTO;
import org.springframework.stereotype.Service;

@Service
public class BookingMapper {

    public BookingDTO mapFromEntity(Booking booking) {
        return new BookingDTO(
                booking.getId(),
                booking.getCreatedAt()
                /*booking.getActivity().getId(),
                booking.getProfile().getId()*/
        );
    }
}
