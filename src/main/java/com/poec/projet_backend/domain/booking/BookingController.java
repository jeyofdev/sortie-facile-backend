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

    @PostMapping(ADD)
    public ResponseEntity<Booking> add(@RequestBody BookingFrontToBackDTO bookingFrontToBackDTO) {
        Booking newBooking = service.add(bookingFrontToBackDTO);

        return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
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
