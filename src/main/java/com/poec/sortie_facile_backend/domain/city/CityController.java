package com.poec.sortie_facile_backend.domain.city;

import com.poec.sortie_facile_backend.domain.city.dto.CityDTO;
import com.poec.sortie_facile_backend.domain.city.dto.SaveCityDTO;
import com.poec.sortie_facile_backend.domain.department.Department;
import com.poec.sortie_facile_backend.domain.department.dto.DepartmentDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
        String roles  = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

        List<City> cityList = cityService.findAll();
        List<CityDTO> cityDTOS = cityList.stream().map(city -> cityMapper.mapFromEntity(city, false, roles.equals("[ROLE_ADMIN]"))).toList();

        return new ResponseEntity<>(cityDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<CityDTO> getById(@PathVariable("id") Long cityId) {
        String roles  = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

        City city = cityService.findById(cityId);
        CityDTO cityDTO = cityMapper.mapFromEntity(city, false, roles.equals("[ROLE_ADMIN]"));

        return new ResponseEntity<>(cityDTO, HttpStatus.OK);
    }

    @GetMapping(ALL + DEPARTMENT + ID)
    public ResponseEntity<List<CityDTO>> getByDepartment(@PathVariable("id") Long departmentId) {
        String roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

        List<City> cityList = cityService.findByDepartment(departmentId);
        List<CityDTO> cityDTOS = cityList.stream().map(department -> cityMapper.mapFromEntity(department, false, roles.equals("[ROLE_ADMIN]"))).toList();

        return new ResponseEntity<>(cityDTOS, HttpStatus.FOUND);
    }

    @PostMapping(ADD + DEPARTMENT + "/{departmentId}")
    public ResponseEntity<CityDTO> add(
            @Valid @RequestBody SaveCityDTO saveCityDTO,
            @PathVariable("departmentId") Long departmentId
    ) {
        City city = cityMapper.mapToEntity(saveCityDTO);
        City newCity = cityService.add(city, departmentId);
        CityDTO newCityDTO = cityMapper.mapFromEntity(newCity, false, false);

        return new ResponseEntity<>(newCityDTO, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<CityDTO> updateById(
            @Valid @RequestBody SaveCityDTO saveCityDTO,
            @PathVariable("id") Long cityId
    ) {
        City city = cityMapper.mapToEntity(saveCityDTO);
        City updatedCity = cityService.updateById(city, cityId);
        CityDTO updatedCityDTO = cityMapper.mapFromEntity(updatedCity, false, false);

        return new ResponseEntity<>(updatedCityDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long cityId) {
        cityService.deleteById(cityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
