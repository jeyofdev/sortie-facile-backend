package com.poec.projet_backend.domain.booking;

import com.poec.projet_backend.domain.activity.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, String>> add(
                                          @PathVariable Long activityId,
                                          @PathVariable Long profileId
    ) {
        service.add(activityId, profileId);
        Map<String, String> response = Map.of("message", "Booking created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
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
