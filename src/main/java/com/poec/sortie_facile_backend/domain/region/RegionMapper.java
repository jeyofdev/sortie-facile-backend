package com.poec.sortie_facile_backend.domain.region;

import com.poec.sortie_facile_backend.common.model.ListRelationWithSizeFormat;
import com.poec.sortie_facile_backend.core.interfaces.BaseDomainMapper;
import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.department.Department;
import com.poec.sortie_facile_backend.domain.profile.Profile;
import com.poec.sortie_facile_backend.domain.region.dto.RegionDTO;
import com.poec.sortie_facile_backend.domain.region.dto.SaveRegionDTO;
import com.poec.sortie_facile_backend.util.Helper;
import org.springframework.stereotype.Service;

@Service
public class RegionMapper implements BaseDomainMapper<Region, RegionDTO, SaveRegionDTO> {
    @Override
    public RegionDTO mapFromEntity(Region region, boolean primaryDataOnly, boolean isAdmin) {
        return new RegionDTO(
                region.getId(),
                Helper.capitalizeEachWord(region.getName()),
                new ListRelationWithSizeFormat<>(
                        region.getDepartmentList().size(),
                        region.getDepartmentList().stream().map(Department::getId).toList()
                ),
                new ListRelationWithSizeFormat<>(
                        region.getActivityList().size(),
                        region.getActivityList().stream().map(Activity::getId).toList()
                ),
                isAdmin ? new ListRelationWithSizeFormat<>(
                    region.getProfileList().size(),
                    region.getProfileList().stream().map(Profile::getId).toList()
                ) : null
        );
    }

    @Override
    public Region mapToEntity(SaveRegionDTO saveRegionDTO) {
        Region region = new Region();
        region.setName(saveRegionDTO.name());

        return region;
    }
}
