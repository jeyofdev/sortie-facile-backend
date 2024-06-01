package com.poec.projet_backend.domain.profile;

import com.poec.projet_backend.domain.city.City;
import com.poec.projet_backend.domain.city.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("profile")
@RequiredArgsConstructor
public class ProfileController {

    @Autowired
    private ProfileService service;

    @GetMapping("/all")
    public ResponseEntity<List<Profile>> getAll() {
        List<Profile> profiles = service.getAll();
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Profile> add(@RequestBody Profile profile) {
        return new ResponseEntity<>(service.add(profile), HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Profile> update(@RequestBody Profile profile, @PathVariable Long id) {
        return new ResponseEntity<>(service.update(profile, id), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
