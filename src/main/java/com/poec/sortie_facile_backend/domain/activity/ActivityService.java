package com.poec.sortie_facile_backend.domain.activity;

import com.poec.sortie_facile_backend.auth_user.AuthUserRepository;
import com.poec.sortie_facile_backend.common.model.DataCountResponse;
import com.poec.sortie_facile_backend.core.abstracts.AbstractDomainService;
import com.poec.sortie_facile_backend.domain.category.Category;
import com.poec.sortie_facile_backend.domain.category.CategoryRepository;
import com.poec.sortie_facile_backend.domain.city.City;
import com.poec.sortie_facile_backend.domain.city.CityRepository;
import com.poec.sortie_facile_backend.domain.department.Department;
import com.poec.sortie_facile_backend.domain.department.DepartmentRepository;
import com.poec.sortie_facile_backend.domain.profile.Profile;
import com.poec.sortie_facile_backend.domain.profile.ProfileRepository;
import com.poec.sortie_facile_backend.domain.region.Region;
import com.poec.sortie_facile_backend.domain.region.RegionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ActivityService extends AbstractDomainService<Activity> {

    private final ActivityRepository activityRepository;
    private final CityRepository cityRepository;
    private final DepartmentRepository departmentRepository;
    private final RegionRepository regionRepository;
    private final CategoryRepository categoryRepository;
    private final ProfileRepository profileRepository;
    private final AuthUserRepository authUserRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository, CityRepository cityRepository, DepartmentRepository departmentRepository, RegionRepository regionRepository, CategoryRepository categoryRepository, ProfileRepository profileRepository, AuthUserRepository authUserRepository) {
        super(activityRepository, "activity");

        this.activityRepository = activityRepository;
        this.cityRepository = cityRepository;
        this.departmentRepository = departmentRepository;
        this.regionRepository = regionRepository;
        this.categoryRepository = categoryRepository;
        this.profileRepository = profileRepository;
        this.authUserRepository = authUserRepository;
    }

    public Activity add(Activity activity, Long regionId, Long departmentId, Long cityId, Long profileId) {
        Region existingRegion = regionRepository.findById(regionId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Region with id " + regionId + " not found")
                );
        Department existingDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Department with id " +  departmentId + " not found")
                );
        City existingCity = cityRepository.findById(cityId)
                .orElseThrow(
                        () -> new EntityNotFoundException("City with id " +  cityId + " not found")
                );
        Profile existingProfile = profileRepository.findById(profileId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Profile with id " +  profileId + " not found")
                );

        List<Category> categoryList = categoryRepository.findAllById(activity.getCategoryList().stream().map(Category::getId).toList());

        activity.setRegion(existingRegion);
        activity.setDepartment(existingDepartment);
        activity.setCity(existingCity);
        activity.setProfile(existingProfile);
        activity.setCategoryList(categoryList);

        return activityRepository.save(activity);
    }

    @Override
    public Activity updateById(Activity activity, Long activityId) {
        String roles  = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        Long userId = authUserRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get().getId();
        Activity existingActivity = findById(activityId);

        if((roles.equals("[ROLE_ADMIN]")) || (roles.equals("[ROLE_USER]") && userId.equals(existingActivity.getProfile().getId()))) {
            existingActivity.setName(activity.getName());
            existingActivity.setAgeMin(activity.getAgeMin());
            existingActivity.setAgeMax(activity.getAgeMax());
            existingActivity.setImgUrl(activity.getImgUrl());
            existingActivity.setLink(activity.getLink());
            existingActivity.setDescription(activity.getDescription());
            existingActivity.setNbGuest(activity.getNbGuest());
            existingActivity.setVisible(activity.isVisible());

            // update region id
            if (activity.getRegion() != null) {
                Region region = regionRepository.findById(activity.getRegion().getId())
                        .orElseThrow(() -> new EntityNotFoundException("Region with id " + activity.getRegion().getId() + " not found"));
                existingActivity.setRegion(region);
            }

            // update department id
            if (activity.getDepartment() != null) {
                Department department = departmentRepository.findById(activity.getDepartment().getId())
                        .orElseThrow(() -> new EntityNotFoundException("Department with id " + activity.getDepartment().getId() + " not found"));
                existingActivity.setDepartment(department);
            }

            // update city id
            if (activity.getCity() != null) {
                City city = cityRepository.findById(activity.getCity().getId())
                        .orElseThrow(() -> new EntityNotFoundException("City with id " + activity.getCity().getId() + " not found"));
                existingActivity.setCity(city);
            }

            // update category ids list
            if (activity.getCategoryList() != null) {
                List<Category> newCategories = categoryRepository.findAllById(activity.getCategoryList().stream()
                        .map(Category::getId)
                        .toList()
                );

                existingActivity.setCategoryList(newCategories);
            }

            return activityRepository.save(existingActivity);
        } else {
            throw new AccessDeniedException("You are not authorized to delete this resource");
        }
    }

    @Override
    public void deleteById(Long activityId) {
        String roles  = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        Long userId = authUserRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get().getId();
        Activity existingActivity = findById(activityId);


        if((roles.equals("[ROLE_ADMIN]")) || (roles.equals("[ROLE_USER]") && userId.equals(existingActivity.getProfile().getId()))) {
            activityRepository.deleteById(activityId);
        } else {
            throw new AccessDeniedException("You are not authorized to delete this resource");
        }
    }

    public DataCountResponse countBookingsByActivityId(Long activityId) {
        return new DataCountResponse(activityRepository.countBookingsByActivityId(activityId));
    }
}
