package com.poec.sortie_facile_backend.domain.department;

import com.poec.sortie_facile_backend.core.interfaces.BaseDomainMapper;
import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.city.City;
import com.poec.sortie_facile_backend.domain.department.dto.DepartmentDTO;
import com.poec.sortie_facile_backend.domain.department.dto.SaveDepartmentDTO;
import org.springframework.stereotype.Service;

@Service
public class DepartmentMapper implements BaseDomainMapper<Department, DepartmentDTO, SaveDepartmentDTO> {
    @Override
    public DepartmentDTO mapFromEntity(Department department) {
        return new DepartmentDTO(
                department.getId(),
                department.getName(),
                department.getRegion().getId(),
                department.getCities().stream().map(City::getId).toList(),
                department.getActivities().stream().map(Activity::getId).toList()
        );
    }

    @Override
    public Department mapToEntity(SaveDepartmentDTO saveDepartmentDTO) {
        Department department = new Department();
        department.setName(saveDepartmentDTO.name());

        return department;
    }
}
