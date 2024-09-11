package com.poec.sortie_facile_backend.domain.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.poec.sortie_facile_backend.core.constants.RouteConstants.*;

@RestController
@RequestMapping(BASE_URL + BOOKING)
@RequiredArgsConstructor
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping(ALL)
    public ResponseEntity<List<BookingDTO>> getAll() {
        List<Booking> bookings = bookingService.findAll();
        List<BookingDTO> bookingDTOS = bookings.stream().map(BookingDTO::mapFromEntity).toList();
        return new ResponseEntity<>(bookingDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<BookingDTO> getById(@PathVariable Long id) {
        Booking newBooking = bookingService.findById(id);
        BookingDTO bookingDTO =BookingDTO.mapFromEntity(newBooking);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    @PostMapping(ADD + ACTIVITY + "/{activityId}" + PROFILE + "/{profileId}")
    public ResponseEntity<Map<String, String>> add(
                                          @PathVariable Long activityId,
                                          @PathVariable Long profileId
    ) {
        bookingService.add(activityId, profileId);
        Map<String, String> response = Map.of("message", "Booking created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<BookingDTO> updateById(@RequestBody Booking booking, @PathVariable Long id) {
        Booking newBooking = bookingService.updateById(booking, id);
        BookingDTO bookingDTO = BookingDTO.mapFromEntity(newBooking);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        bookingService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
