package com.poec.projet_backend.domain.city;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.poec.projet_backend.util.Route.*;

@RestController
@RequestMapping(BASE_URL + CITY)
@RequiredArgsConstructor
public class CityController {

    @Autowired
    private CityService service;

    @GetMapping(ALL)
    public ResponseEntity<List<CityDTO>> getAll() {
        List<City> cities = service.getAll();
        List<CityDTO> cityDTOS = cities.stream().map(CityDTO::mapFromEntity).toList();
        return new ResponseEntity<>(cityDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<CityDTO> getById(@PathVariable Long id) {
        City newCity = service.getById(id);
        CityDTO cityDTO = CityDTO.mapFromEntity(newCity);
        return new ResponseEntity<>(cityDTO, HttpStatus.OK);
    }

    @PostMapping(ADD)
    public ResponseEntity<CityDTO> add(@RequestBody City city) {
        City newCity = service.add(city);
        CityDTO cityDTO = CityDTO.mapFromEntity(newCity);
        return new ResponseEntity<>(cityDTO, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<CityDTO> update(@RequestBody City city, @PathVariable Long id) {
        City newCity = service.update(city, id);
        CityDTO cityDTO = CityDTO.mapFromEntity(newCity);
        return new ResponseEntity<>(cityDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
