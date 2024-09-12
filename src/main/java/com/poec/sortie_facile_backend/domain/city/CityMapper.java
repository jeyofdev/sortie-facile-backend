package com.poec.sortie_facile_backend.domain.city;

import com.poec.sortie_facile_backend.core.interfaces.BaseDomainMapper;
import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.city.dto.CityDTO;
import com.poec.sortie_facile_backend.domain.city.dto.SaveCityDTO;
import org.springframework.stereotype.Service;

@Service
public class CityMapper implements BaseDomainMapper<City, CityDTO, SaveCityDTO> {
    @Override
    public CityDTO mapFromEntity(City city) {
        return new CityDTO(
                city.getId(),
                city.getName(),
                city.getActivities().stream().map(Activity::getId).toList(),
                city.getDepartment().getId()
        );
    }

    @Override
    public City mapToEntity(SaveCityDTO saveCityDTO) {
        City city = new City();
        city.setName(saveCityDTO.name());
        city.setPostalCode(saveCityDTO.postalCode());

        return city;
    }
}
