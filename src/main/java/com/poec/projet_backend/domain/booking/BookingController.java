package com.poec.projet_backend.domain.booking;

import com.poec.projet_backend.domain.city.City;
import com.poec.projet_backend.domain.city.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
public class BookingController {

    @Autowired
    private BookingService service;

    @GetMapping("/all")
    public ResponseEntity<List<BookingDTO>> getAll() {
        List<Booking> bookings = service.getAll();
        List<BookingDTO> bookingDTOS = bookings.stream().map(BookingDTO::mapFromEntity).toList();
        return new ResponseEntity<>(bookingDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public BookingDTO getById(@PathVariable Long id) {
        Booking newBooking = service.getById(id);
        BookingDTO bookingDTO =BookingDTO.mapFromEntity(newBooking);
        return bookingDTO;
    }

    @PostMapping("/add")
    public BookingDTO add(@RequestBody Booking booking) {
        Booking newBooking = service.add(booking);
        BookingDTO bookingDTO =BookingDTO.mapFromEntity(newBooking);
        return bookingDTO;
    }

    @PutMapping("update/{id}")
    public BookingDTO update(@RequestBody Booking booking, @PathVariable Long id) {
        Booking newBooking = service.update(booking, id);
        BookingDTO bookingDTO =BookingDTO.mapFromEntity(newBooking);
        return bookingDTO;
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
