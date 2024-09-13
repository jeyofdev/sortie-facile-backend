package com.poec.sortie_facile_backend.domain.activity;

import com.poec.sortie_facile_backend.core.interfaces.BaseDomainMapper;
import com.poec.sortie_facile_backend.domain.activity.dto.ActivityDTO;
import com.poec.sortie_facile_backend.domain.activity.dto.SaveActivityDTO;
import com.poec.sortie_facile_backend.domain.booking.Booking;
import com.poec.sortie_facile_backend.domain.category.Category;
import com.poec.sortie_facile_backend.domain.city.City;
import com.poec.sortie_facile_backend.domain.department.Department;
import com.poec.sortie_facile_backend.domain.profile.Profile;
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
                Optional.ofNullable(activity.getCity()).map(City::getId).orElse(null),
                Optional.ofNullable(activity.getCategory()).map(Category::getId).orElse(null),
                Optional.ofNullable(activity.getProfile()).map(Profile::getId).orElse(null),
                activity.getBookings().stream().map(Booking::getId).toList()
        );
    }

    @Override
    public Activity mapToEntity(SaveActivityDTO saveActivityDTO) {
        Activity activity = new Activity();

        activity.setName(saveActivityDTO.name());
        activity.setDate(saveActivityDTO.date());
        activity.setAge(saveActivityDTO.age());
        activity.setImgUrl(saveActivityDTO.imgUrl());
        activity.setLink(saveActivityDTO.link());
        activity.setDescription(saveActivityDTO.description());
        activity.setNbGuest(saveActivityDTO.nbGuest());
        activity.setVisible(saveActivityDTO.isVisible());

        return activity;
    }
}
