package com.poec.sortie_facile_backend.domain.city;

import com.poec.sortie_facile_backend.core.abstracts.AbstractDomainService;
import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.department.Department;
import com.poec.sortie_facile_backend.domain.department.DepartmentRepository;
import com.poec.sortie_facile_backend.domain.profile.Profile;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService extends AbstractDomainService<City> {

    private final CityRepository cityRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public CityService(CityRepository cityRepository, DepartmentRepository departmentRepository) {
        super(cityRepository, "city");
        this.cityRepository = cityRepository;
        this.departmentRepository = departmentRepository;
    }

    public City add(City city, Long departmentId) {
        Department newDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Department with id " + departmentId + " not found")
                );

        city.setDepartment(newDepartment);

        return cityRepository.save(city);
    }

    @Override
    public City updateById(City city, Long cityId) {
        City existingCity = findById(cityId);
        existingCity.setName(city.getName());

        if (city.getDepartment() != null) {
            Department department = departmentRepository.findById(city.getDepartment().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Department with id " + city.getDepartment().getId() + " not found"));
            existingCity.setDepartment(department);
        }

        return cityRepository.save(existingCity);
    }

    @Override
    public void deleteById(Long cityId) {
        City city = findById(cityId);

        for (Activity activity : city.getActivityList()) {
            activity.setCity(null);
        }

        for (Profile profile : city.getProfileList()) {
            profile.setCity(null);
        }

        repository.deleteById(cityId);
    }
}
