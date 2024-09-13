package com.poec.sortie_facile_backend.domain.profile;

import com.poec.sortie_facile_backend.domain.profile.dto.ProfileDTO;
import com.poec.sortie_facile_backend.domain.profile.dto.SaveProfileDTO;
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

    @GetMapping(ALL)
    public ResponseEntity<List<ProfileDTO>> getAll() {
        List<Profile> profileList = profileService.findAll();
        List<ProfileDTO> profileDTOS = profileList.stream().map(profileMapper::mapFromEntity).toList();

        return new ResponseEntity<>(profileDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<ProfileDTO> getById(@PathVariable("id") Long profileId) {
        Profile profile = profileService.findById(profileId);
        ProfileDTO profileDTO = profileMapper.mapFromEntity(profile);

        return new ResponseEntity<>(profileDTO, HttpStatus.FOUND);
    }

    @PostMapping(ADD + REGION + "/{regionId}" + DEPARTMENT + "/{departmentId}" + CITY + "/{cityId}" + USER + "/{userId}")
    public ResponseEntity<ProfileDTO> add(
            @RequestBody SaveProfileDTO saveProfileDTO,
            @PathVariable("regionId") Long regionId,
            @PathVariable("departmentId") Long departmentId,
            @PathVariable("cityId") Long cityId,
            @PathVariable("userId") Long userId
    ) {
        Profile activity = profileMapper.mapToEntity(saveProfileDTO);
        Profile newProfile = profileService.add(activity, regionId, departmentId, cityId, userId);
        ProfileDTO newProfileDTO = profileMapper.mapFromEntity(newProfile);

        return new ResponseEntity<>(newProfileDTO, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<ProfileDTO> updateById(
            @RequestBody SaveProfileDTO saveProfileDTO,
            @PathVariable("id") Long profileId
    ) {
        Profile profile = profileMapper.mapToEntity(saveProfileDTO);
        Profile updatedProfile = profileService.updateById(profile, profileId);
        ProfileDTO updatedProfileDTO = profileMapper.mapFromEntity(updatedProfile);

        return new ResponseEntity<>(updatedProfileDTO, HttpStatus.OK);
    }

    /*@PutMapping(UPDATE + "/categories")
    public ResponseEntity<ProfileUpdateCategoriesDTO> updateCategoryInProfile(
            @RequestBody ProfileUpdateCategoriesDTO profileUpdateCategoriesDTO,
            @PathVariable Long id
    ) {
        Profile newProfile = profileService.updateCategoryInProfile(id, profileUpdateCategoriesDTO.categoryIds());
        ProfileUpdateCategoriesDTO newProfileUpdated = profileMapper.mapFromEntityCategory(newProfile);

        return new ResponseEntity<>(newProfileUpdated, HttpStatus.OK);
    }*/

    //-----------------
    // for test
    //-----------------

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long profileId) {
        profileService.deleteById(profileId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


