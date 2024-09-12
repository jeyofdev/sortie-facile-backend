package com.poec.sortie_facile_backend.domain.region;

import com.poec.sortie_facile_backend.core.interfaces.BaseDomainMapper;
import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.department.Department;
import com.poec.sortie_facile_backend.domain.profile.Profile;
import com.poec.sortie_facile_backend.domain.region.dto.RegionDTO;
import com.poec.sortie_facile_backend.domain.region.dto.SaveRegionDTO;
import org.springframework.stereotype.Service;

@Service
public class RegionMapper  implements BaseDomainMapper<Region, RegionDTO, SaveRegionDTO> {
    @Override
    public RegionDTO mapFromEntity(Region region) {
        return new RegionDTO(
                region.getId(),
                region.getName(),
                region.getDepartments().stream().map(Department::getId).toList(),
                region.getActivities().stream().map(Activity::getId).toList()
                /*region.getProfiles().stream().map(Profile::getId).toList()*/
        );
    }

    @Override
    public Region mapToEntity(SaveRegionDTO saveRegionDTO) {
        Region region = new Region();
        region.setName(saveRegionDTO.name());

        return region;
    }
}
