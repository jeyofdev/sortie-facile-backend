package com.poec.sortie_facile_backend.domain.region;

import com.poec.sortie_facile_backend.auth_user.AuthUserRepository;
import com.poec.sortie_facile_backend.domain.region.dto.RegionDTO;
import com.poec.sortie_facile_backend.domain.region.dto.SaveRegionDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.poec.sortie_facile_backend.core.constants.RouteConstants.*;

@RestController
@RequestMapping(BASE_URL + REGION)
@RequiredArgsConstructor
public class RegionController {

    @Autowired
    private RegionService regionService;

    @Autowired
    private RegionMapper regionMapper;

    @Autowired
    private AuthUserRepository authUserRepository;

    @GetMapping(ALL)
    public ResponseEntity<List<RegionDTO>> getAll() {
        String roles  = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

        List<Region> regionList = regionService.findAll();
        List<RegionDTO> regionDTOS = regionList.stream().map(region -> regionMapper.mapFromEntity(
                region,
                false,
                roles.equals("[ROLE_ADMIN]"))).toList();

        return new ResponseEntity<>(regionDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<RegionDTO> getById(@PathVariable("id") Long regionId) {
        String roles  = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        Region region = regionService.findById(regionId);
        RegionDTO regionDTO = regionMapper.mapFromEntity(region, false, roles.equals("[ROLE_ADMIN]"));

        return new ResponseEntity<>(regionDTO, HttpStatus.FOUND);
    }

    @PostMapping(ADD)
    public ResponseEntity<RegionDTO> add(@Valid @RequestBody SaveRegionDTO saveRegionDTO) {
        Region region = regionMapper.mapToEntity(saveRegionDTO);
        Region newRegion = regionService.add(region);
        RegionDTO newRegionDTO = regionMapper.mapFromEntity(newRegion, false, false);

        return new ResponseEntity<>(newRegionDTO, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<RegionDTO> updateById(
            @Valid @RequestBody SaveRegionDTO saveRegionDTO,
            @PathVariable("id") Long regionId
    ) {
        Region region = regionMapper.mapToEntity(saveRegionDTO);
        Region updatedRegion = regionService.updateById(region, regionId);
        RegionDTO updatedRegionDTO = regionMapper.mapFromEntity(updatedRegion, false, false);

        return new ResponseEntity<>(updatedRegionDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long regionId) {
        regionService.deleteById(regionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
