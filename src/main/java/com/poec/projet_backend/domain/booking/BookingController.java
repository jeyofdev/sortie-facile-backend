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
    public ResponseEntity<List<Booking>> getAll() {
        List<Booking> bookings = service.getAll();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Booking> add(@RequestBody Booking booking) {
        return new ResponseEntity<>(service.add(booking), HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Booking> update(@RequestBody Booking booking, @PathVariable Long id) {
        return new ResponseEntity<>(service.update(booking, id), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
