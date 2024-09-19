package com.poec.sortie_facile_backend.domain.city;

import com.poec.sortie_facile_backend.domain.city.dto.CityDTO;
import com.poec.sortie_facile_backend.domain.city.dto.SaveCityDTO;
import jakarta.validation.Valid;
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
        List<CityDTO> cityDTOS = cityList.stream().map(city -> cityMapper.mapFromEntity(city, false)).toList();

        return new ResponseEntity<>(cityDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<CityDTO> getById(@PathVariable("id") Long cityId) {
        City city = cityService.findById(cityId);
        CityDTO cityDTO = cityMapper.mapFromEntity(city, false);

        return new ResponseEntity<>(cityDTO, HttpStatus.FOUND);
    }

    @PostMapping(ADD + DEPARTMENT + "/{departmentId}")
    public ResponseEntity<CityDTO> add(
            @Valid @RequestBody SaveCityDTO saveCityDTO,
            @PathVariable("departmentId") Long departmentId
    ) {
        City city = cityMapper.mapToEntity(saveCityDTO);
        City newCity = cityService.add(city, departmentId);
        CityDTO newCityDTO = cityMapper.mapFromEntity(newCity, false);

        return new ResponseEntity<>(newCityDTO, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<CityDTO> updateById(
            @Valid @RequestBody SaveCityDTO saveCityDTO,
            @PathVariable("id") Long cityId
    ) {
        City city = cityMapper.mapToEntity(saveCityDTO);
        City updatedCity = cityService.updateById(city, cityId);
        CityDTO updatedCityDTO = cityMapper.mapFromEntity(updatedCity, false);

        return new ResponseEntity<>(updatedCityDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long cityId) {
        cityService.deleteById(cityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
