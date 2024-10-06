package com.poec.sortie_facile_backend.domain.profile;

import com.poec.sortie_facile_backend.domain.profile.dto.ProfileDTO;
import com.poec.sortie_facile_backend.domain.profile.dto.SaveProfileDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.poec.sortie_facile_backend.core.constants.RouteConstants.*;

@RestController
@RequestMapping(BASE_URL + PROFILE)
@RequiredArgsConstructor
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileMapper profileMapper;

    @GetMapping()
    public ResponseEntity<List<ProfileDTO>> getAll() {
        List<Profile> profileList = profileService.findAll();
        List<ProfileDTO> profileDTOS = profileList.stream().map(profile -> profileMapper.mapFromEntity(profile, false, false)).toList();

        return new ResponseEntity<>(profileDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<ProfileDTO> getById(@PathVariable("id") Long profileId) {
        Profile profile = profileService.findById(profileId);
        ProfileDTO profileDTO = profileMapper.mapFromEntity(profile, false, false);

        return new ResponseEntity<>(profileDTO, HttpStatus.OK);
    }

    @PostMapping(ADD + REGION + "/{regionId}" + DEPARTMENT + "/{departmentId}" + CITY + "/{cityId}" + USER + "/{userId}")
    public ResponseEntity<ProfileDTO> add(
            @Valid @RequestBody SaveProfileDTO saveProfileDTO,
            @PathVariable("regionId") Long regionId,
            @PathVariable("departmentId") Long departmentId,
            @PathVariable("cityId") Long cityId,
            @PathVariable("userId") Long userId
    ) {
        Profile activity = profileMapper.mapToEntity(saveProfileDTO);
        Profile newProfile = profileService.add(activity, regionId, departmentId, cityId, userId);
        ProfileDTO newProfileDTO = profileMapper.mapFromEntity(newProfile, false, false);

        return new ResponseEntity<>(newProfileDTO, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<ProfileDTO> updateById(
            @Valid @RequestBody SaveProfileDTO saveProfileDTO,
            @PathVariable("id") Long profileId
    ) {
        Profile profile = profileMapper.mapToEntity(saveProfileDTO);
        Profile updatedProfile = profileService.updateById(profile, profileId);
        ProfileDTO updatedProfileDTO = profileMapper.mapFromEntity(updatedProfile, false, false);

        return new ResponseEntity<>(updatedProfileDTO, HttpStatus.OK);
    }

    //-----------------
    // for test
    //-----------------

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long profileId) {
        profileService.deleteById(profileId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


