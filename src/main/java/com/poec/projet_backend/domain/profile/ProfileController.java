package com.poec.projet_backend.domain.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    @Autowired
    private ProfileService service;

    @GetMapping("/all")
    public ResponseEntity<List<ProfileDTO>> getAll() {
        List<Profile> profiles = service.getAll();
        List<ProfileDTO> profileDTOS = profiles.stream().map(ProfileDTO::mapFromEntity).toList();
        return new ResponseEntity<>(profileDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getById(@PathVariable Long id) {
        Profile newProfile = service.getById(id);
        ProfileDTO profileDTO = ProfileDTO.mapFromEntity(newProfile);
        return new ResponseEntity<>(profileDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ProfileDTO> add(@RequestBody ProfileDTO profile) {
        System.out.println("ca marche?"+ profile.toString());
//        Profile newProfile = service.add(profile);
//        ProfileDTO profileDTO = ProfileDTO.mapFromEntity(newProfile);
        return new ResponseEntity<>(profile, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ProfileDTO> update(@RequestBody Profile profile, @PathVariable Long id) {
        Profile newProfile = service.update(profile, id);
        ProfileDTO profileDTO = ProfileDTO.mapFromEntity(newProfile);
        return new ResponseEntity<>(profileDTO, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
