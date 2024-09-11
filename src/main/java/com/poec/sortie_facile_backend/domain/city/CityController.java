package com.poec.sortie_facile_backend.domain.city;

import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.activity.dto.ActivityDTO;
import com.poec.sortie_facile_backend.domain.city.dto.CityDTO;
import com.poec.sortie_facile_backend.domain.city.dto.SaveCityDTO;
import com.poec.sortie_facile_backend.domain.region.Region;
import com.poec.sortie_facile_backend.domain.region.dto.RegionDTO;
import com.poec.sortie_facile_backend.domain.region.dto.SaveRegionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.poec.sortie_facile_backend.core.constants.RouteConstants.*;

@RestController
@RequestMapping(BASE_URL + CITY)
@RequiredArgsConstructor
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private CityMapper cityMapper;

    @GetMapping(ALL)
    public ResponseEntity<List<CityDTO>> getAll() {
        List<City> cityList = cityService.findAll();
        List<CityDTO> cityDTOS = cityList.stream().map(cityMapper::mapFromEntity).toList();

        return new ResponseEntity<>(cityDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<CityDTO> getById(@PathVariable("id") Long cityId) {
        City city = cityService.findById(cityId);
        CityDTO cityDTO = cityMapper.mapFromEntity(city);

        return new ResponseEntity<>(cityDTO, HttpStatus.FOUND);
    }

    @PostMapping(ADD + DEPARTMENT + "/{departmentId}")
    public ResponseEntity<CityDTO> add(@RequestBody SaveCityDTO saveCityDTO, @PathVariable("departmentId") Long departmentId) {
        City city = cityMapper.mapToEntity(saveCityDTO);
        City newCity = cityService.add(city, departmentId);
        CityDTO newCityDTO = cityMapper.mapFromEntity(newCity);

        return new ResponseEntity<>(newCityDTO, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<CityDTO> updateById(@RequestBody SaveCityDTO saveCityDTO, @PathVariable("id") Long cityId) {
        City city = cityMapper.mapToEntity(saveCityDTO);
        City updatedCity = cityService.updateById(city, cityId);
        CityDTO updatedCityDTO = cityMapper.mapFromEntity(updatedCity);

        return new ResponseEntity<>(updatedCityDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable Long cityId) {
        cityService.deleteById(cityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
