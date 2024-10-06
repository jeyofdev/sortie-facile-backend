package com.poec.sortie_facile_backend.domain.department;

import com.poec.sortie_facile_backend.common.model.ListRelationWithSizeFormat;
import com.poec.sortie_facile_backend.core.interfaces.BaseDomainMapper;
import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.city.City;
import com.poec.sortie_facile_backend.domain.department.dto.DepartmentDTO;
import com.poec.sortie_facile_backend.domain.department.dto.SaveDepartmentDTO;
import com.poec.sortie_facile_backend.domain.profile.Profile;
import com.poec.sortie_facile_backend.domain.region.Region;
import com.poec.sortie_facile_backend.util.Helper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentMapper implements BaseDomainMapper<Department, DepartmentDTO, SaveDepartmentDTO> {
    @Override
    public DepartmentDTO mapFromEntity(Department department, boolean primaryDataOnly, boolean isAdmin) {
        return new DepartmentDTO(
                department.getId(),
                Helper.capitalizeEachWord(department.getName()),
                department.getNumber(),
                new ListRelationWithSizeFormat<>(
                        department.getActivityList().size(),
                        department.getActivityList().stream().map(Activity::getId).toList()
                ),
                Optional.ofNullable(department.getRegion()).map(Region::getId).orElse(null),
                new ListRelationWithSizeFormat<>(
                        department.getCityList().size(),
                        department.getCityList().stream().map(City::getId).toList()
                ),
                isAdmin ? new ListRelationWithSizeFormat<>(
                        department.getProfileList().size(),
                        department.getProfileList().stream().map(Profile::getId).toList()
                ) : null
        );
    }

    @Override
    public Department mapToEntity(SaveDepartmentDTO saveDepartmentDTO) {
        Department department = new Department();
        department.setName(saveDepartmentDTO.name());
        department.setNumber(saveDepartmentDTO.number());

        if (saveDepartmentDTO.regionId() != null) {
            Region region = new Region();
            region.setId(saveDepartmentDTO.regionId());
            department.setRegion(region);
        }

        return department;
    }
}
