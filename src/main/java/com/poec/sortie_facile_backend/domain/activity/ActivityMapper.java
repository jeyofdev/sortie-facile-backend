package com.poec.sortie_facile_backend.domain.activity;

import com.poec.sortie_facile_backend.core.interfaces.BaseDomainMapper;
import com.poec.sortie_facile_backend.domain.activity.dto.ActivityDTO;
import com.poec.sortie_facile_backend.domain.activity.dto.SaveActivityDTO;
import org.springframework.stereotype.Service;

@Service
public class ActivityMapper implements BaseDomainMapper<Activity, ActivityDTO, SaveActivityDTO> {

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

    public Activity mapToEntity(SaveActivityDTO saveCategoryDTO) {
        return new Activity(
                saveCategoryDTO.name(),
                saveCategoryDTO.date(),
                saveCategoryDTO.age(),
                saveCategoryDTO.imgUrl(),
                saveCategoryDTO.link(),
                saveCategoryDTO.description(),
                saveCategoryDTO.nbGuest(),
                saveCategoryDTO.isVisible()
        );
    }
}
