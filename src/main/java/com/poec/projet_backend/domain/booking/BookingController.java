package com.poec.projet_backend.domain.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.poec.projet_backend.util.Route.*;

@RestController
@RequestMapping(BASE_URL + BOOKING)
@RequiredArgsConstructor
public class BookingController {

    @Autowired
    private BookingService service;

    @GetMapping(ALL)
    public ResponseEntity<List<BookingDTO>> getAll() {
        List<Booking> bookings = service.getAll();
        List<BookingDTO> bookingDTOS = bookings.stream().map(BookingDTO::mapFromEntity).toList();
        return new ResponseEntity<>(bookingDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<BookingDTO> getById(@PathVariable Long id) {
        Booking newBooking = service.getById(id);
        BookingDTO bookingDTO =BookingDTO.mapFromEntity(newBooking);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    @PostMapping(ADD + ACTIVITY + "/{activityId}" + PROFILE + "/{profileId}")
    public ResponseEntity<BookingDTO> add(@RequestBody Booking booking,
                                          @PathVariable Long activityId,
                                          @PathVariable Long profileId) {
        Booking newBooking = service.add(booking, activityId, profileId);
        BookingDTO bookingDTO = BookingDTO.mapFromEntity(newBooking);
        return new ResponseEntity<>(bookingDTO, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<BookingDTO> update(@RequestBody Booking booking, @PathVariable Long id) {
        Booking newBooking = service.update(booking, id);
        BookingDTO bookingDTO =BookingDTO.mapFromEntity(newBooking);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
