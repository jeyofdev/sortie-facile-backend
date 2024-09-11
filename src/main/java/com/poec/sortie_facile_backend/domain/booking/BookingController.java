package com.poec.sortie_facile_backend.domain.booking;

import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.activity.ActivityMapper;
import com.poec.sortie_facile_backend.domain.activity.dto.ActivityDTO;
import com.poec.sortie_facile_backend.domain.booking.dto.BookingDTO;
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

    @Autowired
    private BookingMapper bookingMapper;

    @GetMapping(ALL)
    public ResponseEntity<List<BookingDTO>> getAll() {
        List<Booking> bookingList = bookingService.findAll();
        List<BookingDTO> bookingDTOS = bookingList.stream().map(bookingMapper::mapFromEntity).toList();

        return new ResponseEntity<>(bookingDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<BookingDTO> getById(@PathVariable("id") Long bookingId) {
        Booking booking = bookingService.findById(bookingId);
        BookingDTO bookingDTO = bookingMapper.mapFromEntity(booking);

        return new ResponseEntity<>(bookingDTO, HttpStatus.FOUND);
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
    public ResponseEntity<BookingDTO> updateById(@RequestBody Booking booking, @PathVariable("id") Long bookingId) {
        Booking newBooking = bookingService.updateById(booking, bookingId);
        BookingDTO bookingDTO = bookingMapper.mapFromEntity(newBooking);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        bookingService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
