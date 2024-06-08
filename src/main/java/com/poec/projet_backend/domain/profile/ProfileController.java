package com.poec.projet_backend.domain.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.poec.projet_backend.util.Route.*;

@RestController
@RequestMapping(BASE_URL + PROFILE)
@RequiredArgsConstructor
public class ProfileController {

    @Autowired
    private ProfileService service;

@GetMapping(ALL)
    public ResponseEntity<List<ProfileDTO>> getAll() {
        List<Profile> profiles = service.getAll();
        List<ProfileDTO> profileDTOS = profiles.stream().map(ProfileDTO::mapFromEntity).toList();
        return new ResponseEntity<>(profileDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<ProfileDTO> getById(@PathVariable Long id) {
        Profile newProfile = service.getById(id);
        ProfileDTO profileDTO = ProfileDTO.mapFromEntity(newProfile);
        return new ResponseEntity<>(profileDTO, HttpStatus.OK);
    }

    @PostMapping(ADD)
    public ResponseEntity<Profile> add(@RequestBody ProfileFromFrontToBackDTO profileBackDTO) {
        Profile newProfile = service.add(profileBackDTO);
        return new ResponseEntity<>(newProfile, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<ProfileDTO> update(@RequestBody Profile profile, @PathVariable Long id) {
        Profile newProfile = service.update(profile, id);
        ProfileDTO profileDTO = ProfileDTO.mapFromEntity(newProfile);
        return new ResponseEntity<>(profileDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
