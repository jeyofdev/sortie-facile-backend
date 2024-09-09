package com.poec.sortie_facile_backend.domain.city;

import com.poec.sortie_facile_backend.domain.department.Department;
import com.poec.sortie_facile_backend.domain.department.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityService {

    @Autowired
    private CityRepository repository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<City> getAll() {
        return repository.findAll();
    }

    public City getById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                () -> new EntityNotFoundException(id + " not fouund")
        );
    }

    public City add(City city, Long departmentId) {
        Department newDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(
                        () -> new EntityNotFoundException(departmentId + " not found")
                );
        city.setDepartment(newDepartment);
        return repository.save(city);
    }

    public City update(City city, Long id) {
        City newCity = getById(id);
        newCity.setName(city.getName());

        return repository.save(newCity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
