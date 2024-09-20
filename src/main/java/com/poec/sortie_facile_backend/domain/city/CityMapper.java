package com.poec.sortie_facile_backend.domain.city;

import com.poec.sortie_facile_backend.core.interfaces.BaseDomainMapper;
import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.city.dto.CityDTO;
import com.poec.sortie_facile_backend.domain.city.dto.SaveCityDTO;
import com.poec.sortie_facile_backend.domain.department.Department;
import com.poec.sortie_facile_backend.domain.profile.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityMapper implements BaseDomainMapper<City, CityDTO, SaveCityDTO> {
    @Override
    public CityDTO mapFromEntity(City city, boolean primaryDataOnly, boolean isAdmin) {
        return new CityDTO(
                city.getId(),
                city.getName(),
                city.getZipCode(),
                city.getActivityList().stream().map(Activity::getId).toList(),
                Optional.ofNullable(city.getDepartment()).map(Department::getId).orElse(null),
                isAdmin ? city.getProfileList().stream().map(Profile::getId).toList() : null
        );
    }

    @Override
    public City mapToEntity(SaveCityDTO saveCityDTO) {
        City city = new City();
        city.setName(saveCityDTO.name());
        city.setZipCode(saveCityDTO.zipCode());

        if (saveCityDTO.departmentId() != null) {
            Department department = new Department();
            department.setId(saveCityDTO.departmentId());
            city.setDepartment(department);
        }

        return city;
    }
}
