package com.poec.sortie_facile_backend.domain.activity;

import com.poec.sortie_facile_backend.common.model.AgeFormat;
import com.poec.sortie_facile_backend.common.model.LocationFormat;
import com.poec.sortie_facile_backend.core.interfaces.BaseDomainMapper;
import com.poec.sortie_facile_backend.domain.activity.dto.ActivityDTO;
import com.poec.sortie_facile_backend.domain.activity.dto.SaveActivityDTO;
import com.poec.sortie_facile_backend.domain.booking.Booking;
import com.poec.sortie_facile_backend.domain.category.Category;
import com.poec.sortie_facile_backend.domain.category.CategoryMapper;
import com.poec.sortie_facile_backend.domain.category.CategoryRepository;
import com.poec.sortie_facile_backend.domain.city.City;
import com.poec.sortie_facile_backend.domain.city.dto.CityDTO;
import com.poec.sortie_facile_backend.domain.department.Department;
import com.poec.sortie_facile_backend.domain.department.dto.DepartmentDTO;
import com.poec.sortie_facile_backend.domain.profile.Profile;
import com.poec.sortie_facile_backend.domain.region.Region;
import com.poec.sortie_facile_backend.domain.region.dto.RegionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityMapper implements BaseDomainMapper<Activity, ActivityDTO, SaveActivityDTO> {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public ActivityMapper(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public ActivityDTO mapFromEntity(Activity activity, boolean primaryDataOnly) {
        return new ActivityDTO(
                activity.getId(),
                activity.getName(),
                activity.getCreatedAt(),
                new AgeFormat(
                    activity.getAgeMin(),
                    activity.getAgeMax()
                ),
                activity.getImgUrl(),
                activity.getLink(),
                activity.getDescription(),
                activity.getNbGuest(),
                activity.isVisible(),
                new LocationFormat(
                        new RegionDTO(activity.getRegion().getId(), activity.getRegion().getName(), null, null, null),
                        new DepartmentDTO(activity.getDepartment().getId(), activity.getDepartment().getName(), activity.getDepartment().getNumber(), null, null, null, null),
                        new CityDTO(activity.getCity().getId(), activity.getCity().getName(), activity.getCity().getZipCode(), null, null, null)
                ),
                activity.getCategoryList().stream().map(
                        category -> categoryMapper.mapFromEntity(category, true)
                ).toList(),
                Optional.ofNullable(activity.getProfile()).map(Profile::getId).orElse(null),
                activity.getBookingList().stream().map(Booking::getId).toList()
        );
    }

    @Override
    public Activity mapToEntity(SaveActivityDTO saveActivityDTO) {
        Activity activity = new Activity();

        activity.setName(saveActivityDTO.name());
        activity.setCreatedAt(new Date());
        activity.setAgeMin(saveActivityDTO.ageMin());
        activity.setAgeMax(saveActivityDTO.ageMax());
        activity.setImgUrl(saveActivityDTO.imgUrl());
        activity.setLink(saveActivityDTO.link());
        activity.setDescription(saveActivityDTO.description());
        activity.setNbGuest(saveActivityDTO.nbGuest());
        activity.setVisible(saveActivityDTO.isVisible());

        if (saveActivityDTO.regionId() != null) {
            Region region = new Region();
            region.setId(saveActivityDTO.regionId());
            activity.setRegion(region);
        }

        if (saveActivityDTO.departmentId() != null) {
            Department department = new Department();
            department.setId(saveActivityDTO.departmentId());
            activity.setDepartment(department);
        }

        if (saveActivityDTO.cityId() != null) {
            City city = new City();
            city.setId(saveActivityDTO.cityId());
            activity.setCity(city);
        }

        if (saveActivityDTO.categoryIds() != null) {
            List<Category> categoryList = categoryRepository.findAllById(saveActivityDTO.categoryIds());
            activity.setCategoryList(categoryList);
        }

        if (saveActivityDTO.creatorUserId() != null) {
            Profile profile = new Profile();
            profile.setId(saveActivityDTO.creatorUserId());
            activity.setProfile(profile);
        }

        return activity;
    }
}
