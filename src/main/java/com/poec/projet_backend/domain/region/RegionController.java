package com.poec.projet_backend.domain.region;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.poec.projet_backend.util.Route.*;

@RestController
@RequestMapping(BASE_URL + REGION)
@RequiredArgsConstructor
public class RegionController {

    @Autowired
    private RegionService service;

    @GetMapping(ALL)
    public ResponseEntity<List<RegionDTO>> getAll() {
        List<Region> regions = service.getAll();
        List<RegionDTO> regionDTOS = regions.stream().map(RegionDTO::mapFromEntity).toList();
        return new ResponseEntity<>(regionDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<RegionDTO> getById(@PathVariable Long id) {
        Region newRegion = service.getById(id);
        RegionDTO regionDTO = RegionDTO.mapFromEntity(newRegion);
        return new ResponseEntity<>(regionDTO, HttpStatus.OK);
    }

    @PostMapping(ADD)
    public ResponseEntity<RegionDTO> add(@RequestBody Region region) {
        Region newRegion = service.add(region);
        RegionDTO regionDTO = RegionDTO.mapFromEntity(newRegion);
        return new ResponseEntity<>(regionDTO, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<RegionDTO> update(@RequestBody Region region, @PathVariable Long id) {
        Region newRegion = service.update(region, id);
        RegionDTO regionDTO = RegionDTO.mapFromEntity(newRegion);
        return new ResponseEntity<>(regionDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
