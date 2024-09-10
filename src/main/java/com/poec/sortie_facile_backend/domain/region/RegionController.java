package com.poec.sortie_facile_backend.domain.region;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.poec.sortie_facile_backend.core.constants.RouteConstants.*;

@RestController
@RequestMapping(BASE_URL + REGION)
@RequiredArgsConstructor
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping(ALL)
    public ResponseEntity<List<RegionDTO>> getAll() {
        List<Region> regions = regionService.findAll();
        List<RegionDTO> regionDTOS = regions.stream().map(RegionDTO::mapFromEntity).toList();
        return new ResponseEntity<>(regionDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<RegionDTO> getById(@PathVariable Long id) {
        Region newRegion = regionService.findById(id);
        RegionDTO regionDTO = RegionDTO.mapFromEntity(newRegion);
        return new ResponseEntity<>(regionDTO, HttpStatus.FOUND);
    }

    @PostMapping(ADD)
    public ResponseEntity<RegionDTO> add(@RequestBody Region region) {
        Region newRegion = regionService.add(region);
        RegionDTO regionDTO = RegionDTO.mapFromEntity(newRegion);
        return new ResponseEntity<>(regionDTO, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    @Transactional
    public ResponseEntity<RegionDTO> update(@RequestBody Region region, @PathVariable Long id) {
        Region newRegion = regionService.updateById(region, id);
        RegionDTO regionDTO = RegionDTO.mapFromEntity(newRegion);
        return new ResponseEntity<>(regionDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        regionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
