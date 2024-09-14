package com.poec.sortie_facile_backend.domain.department;

import com.poec.sortie_facile_backend.core.interfaces.BaseDomainMapper;
import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.city.City;
import com.poec.sortie_facile_backend.domain.department.dto.DepartmentDTO;
import com.poec.sortie_facile_backend.domain.department.dto.SaveDepartmentDTO;
import com.poec.sortie_facile_backend.domain.profile.Profile;
import com.poec.sortie_facile_backend.domain.region.Region;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentMapper implements BaseDomainMapper<Department, DepartmentDTO, SaveDepartmentDTO> {
    @Override
    public DepartmentDTO mapFromEntity(Department department) {
        return new DepartmentDTO(
                department.getId(),
                department.getName(),
                department.getActivities().stream().map(Activity::getId).toList(),
                Optional.ofNullable(department.getRegion()).map(Region::getId).orElse(null),
                department.getCities().stream().map(City::getId).toList(),
                department.getProfiles().stream().map(Profile::getId).toList()
        );
    }

    @Override
    public Department mapToEntity(SaveDepartmentDTO saveDepartmentDTO) {
        Department department = new Department();
        department.setName(saveDepartmentDTO.name());

        if (saveDepartmentDTO.regionId() != null) {
            Region region = new Region();
            region.setId(saveDepartmentDTO.regionId());
            department.setRegion(region);
        }

        return department;
    }
}
