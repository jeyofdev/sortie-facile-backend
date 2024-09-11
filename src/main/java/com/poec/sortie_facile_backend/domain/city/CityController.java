package com.poec.sortie_facile_backend.domain.city;

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

    @GetMapping(ALL)
    public ResponseEntity<List<CityDTO>> getAll() {
        List<City> cities = cityService.findAll();
        List<CityDTO> cityDTOS = cities.stream().map(CityDTO::mapFromEntity).toList();
        return new ResponseEntity<>(cityDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<CityDTO> getById(@PathVariable Long id) {
        City newCity = cityService.findById(id);
        CityDTO cityDTO = CityDTO.mapFromEntity(newCity);
        return new ResponseEntity<>(cityDTO, HttpStatus.FOUND);
    }

    @PostMapping(ADD + DEPARTMENT + "/{departmentId}")
    public ResponseEntity<CityDTO> add(@RequestBody City city, @PathVariable Long departmentId) {
        City newCity = cityService.add(city, departmentId);
        CityDTO cityDTO = CityDTO.mapFromEntity(newCity);
        return new ResponseEntity<>(cityDTO, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<CityDTO> update(@RequestBody City city, @PathVariable Long id) {
        City newCity = cityService.updateById(city, id);
        CityDTO cityDTO = CityDTO.mapFromEntity(newCity);
        return new ResponseEntity<>(cityDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cityService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
