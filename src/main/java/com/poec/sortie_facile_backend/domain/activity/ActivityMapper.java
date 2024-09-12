package com.poec.sortie_facile_backend.domain.activity;

import com.poec.sortie_facile_backend.core.interfaces.BaseDomainMapper;
import com.poec.sortie_facile_backend.domain.activity.dto.ActivityDTO;
import com.poec.sortie_facile_backend.domain.activity.dto.SaveActivityDTO;
import com.poec.sortie_facile_backend.domain.city.City;
import com.poec.sortie_facile_backend.domain.department.Department;
import com.poec.sortie_facile_backend.domain.region.Region;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActivityMapper implements BaseDomainMapper<Activity, ActivityDTO, SaveActivityDTO> {

    @Override
    public ActivityDTO mapFromEntity(Activity activity) {
        return new ActivityDTO(
                activity.getId(),
                activity.getName(),
                activity.getDate(),
                activity.getAge(),
                activity.getImgUrl(),
                activity.getLink(),
                activity.getDescription(),
                activity.getNbGuest(),
                activity.isVisible(),
                Optional.ofNullable(activity.getRegion()).map(Region::getId).orElse(null),
                Optional.ofNullable(activity.getDepartment()).map(Department::getId).orElse(null),
                Optional.ofNullable(activity.getCity()).map(City::getId).orElse(null)
                /*activity.getProfile().getId()*/
        );
    }

    @Override
    public Activity mapToEntity(SaveActivityDTO saveActivityDTO) {
        return new Activity(
                saveActivityDTO.name(),
                saveActivityDTO.date(),
                saveActivityDTO.age(),
                saveActivityDTO.imgUrl(),
                saveActivityDTO.link(),
                saveActivityDTO.description(),
                saveActivityDTO.nbGuest(),
                saveActivityDTO.isVisible()
        );
    }
}
