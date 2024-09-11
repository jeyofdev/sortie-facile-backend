package com.poec.sortie_facile_backend.domain.activity;

import com.poec.sortie_facile_backend.core.interfaces.BaseDomainMapper;
import com.poec.sortie_facile_backend.domain.activity.dto.ActivityDTO;
import com.poec.sortie_facile_backend.domain.activity.dto.SaveActivityDTO;
import org.springframework.stereotype.Service;

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
                activity.getRegion().getId(),
                activity.getDepartment().getId(),
                activity.getCity().getId(),
                activity.getProfile().getId()
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
