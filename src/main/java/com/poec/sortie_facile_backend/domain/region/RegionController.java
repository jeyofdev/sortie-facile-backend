package com.poec.sortie_facile_backend.domain.region;

import com.poec.sortie_facile_backend.domain.region.dto.RegionDTO;
import com.poec.sortie_facile_backend.domain.region.dto.SaveRegionDTO;
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

    @Autowired
    private RegionMapper regionMapper;

    @GetMapping(ALL)
    public ResponseEntity<List<RegionDTO>> getAll() {
        List<Region> regionList = regionService.findAll();
        List<RegionDTO> regionDTOS = regionList.stream().map(regionMapper::mapFromEntity).toList();

        return new ResponseEntity<>(regionDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<RegionDTO> getById(@PathVariable("id") Long regionId) {
        Region region = regionService.findById(regionId);
        RegionDTO regionDTO = regionMapper.mapFromEntity(region);

        return new ResponseEntity<>(regionDTO, HttpStatus.FOUND);
    }

    @PostMapping(ADD)
    public ResponseEntity<RegionDTO> add(@RequestBody SaveRegionDTO saveRegionDTO) {
        Region region = regionMapper.mapToEntity(saveRegionDTO);
        Region newRegion = regionService.add(region);
        RegionDTO newRegionDTO = regionMapper.mapFromEntity(newRegion);

        return new ResponseEntity<>(newRegionDTO, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<RegionDTO> updateById(@RequestBody SaveRegionDTO saveRegionDTO, @PathVariable("id") Long regionId) {
        Region region = regionMapper.mapToEntity(saveRegionDTO);
        Region updatedRegion = regionService.updateById(region, regionId);
        RegionDTO updatedRegionDTO = regionMapper.mapFromEntity(updatedRegion);

        return new ResponseEntity<>(updatedRegionDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long regionId) {
        regionService.deleteById(regionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
