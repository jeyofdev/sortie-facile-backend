package com.poec.projet_backend.domain.city;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/city")
@RequiredArgsConstructor
public class CityController {

    @Autowired
    private CityService service;

    @GetMapping("/all")
    public ResponseEntity<List<CityDTO>> getAll() {
        List<City> cities = service.getAll();
        List<CityDTO> cityDTOS = cities.stream().map(com.poec.projet_backend.domain.city.CityDTO::mapFromEntity).toList();
        return new ResponseEntity<>(cityDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public CityDTO getById(@PathVariable Long id) {
        City newCity = service.getById(id);
        CityDTO cityDTO = com.poec.projet_backend.domain.city.CityDTO.mapFromEntity(newCity);
        return cityDTO;
    }

    @PostMapping("/add")
    public CityDTO add(@RequestBody City city) {
        City newCity = service.add(city);
        CityDTO cityDTO = com.poec.projet_backend.domain.city.CityDTO.mapFromEntity(newCity);
        return cityDTO;
    }

    @PutMapping("update/{id}")
    public CityDTO update(@RequestBody City city, @PathVariable Long id) {
        City newCity = service.update(city, id);
        CityDTO cityDTO = com.poec.projet_backend.domain.city.CityDTO.mapFromEntity(newCity);
        return cityDTO;
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
