package com.poec.projet_backend.domain.region;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/region")
@RequiredArgsConstructor
public class RegionController {

    @Autowired
    private RegionService service;

    @GetMapping("/all")
    public ResponseEntity<List<RegionDTO>> getAll() {
        List<Region> regions = service.getAll();
        List<RegionDTO> regionDTOS = regions.stream().map(RegionDTO::mapFromEntity).toList();
        return new ResponseEntity<>(regionDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public RegionDTO getById(@PathVariable Long id) {
        Region newRegion = service.getById(id);
        RegionDTO regionDTO = RegionDTO.mapFromEntity(newRegion);
        return regionDTO;
    }

    @PostMapping("/add")
    public RegionDTO add(@RequestBody Region region) {
        Region newRegion = service.add(region);
        RegionDTO regionDTO = RegionDTO.mapFromEntity(newRegion);
        return regionDTO;
    }

    @PutMapping("update/{id}")
    public RegionDTO update(@RequestBody Region region, @PathVariable Long id) {
        Region newRegion = service.update(region, id);
        RegionDTO regionDTO = RegionDTO.mapFromEntity(newRegion);
        return regionDTO;
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
