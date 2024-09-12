package com.poec.sortie_facile_backend.domain.activity;

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
import org.springframework.stereotype.Service;

@Service
public class ActivityService extends AbstractDomainService<Activity> {

    private final ActivityRepository activityRepository;
    private final CityRepository cityRepository;
    private final DepartmentRepository departmentRepository;
    private final RegionRepository regionRepository;
    private final CategoryRepository categoryRepository;
    private final ProfileRepository profileRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository, CityRepository cityRepository, DepartmentRepository departmentRepository, RegionRepository regionRepository, CategoryRepository categoryRepository, ProfileRepository profileRepository) {
        super(activityRepository, "activity");

        this.activityRepository = activityRepository;
        this.cityRepository = cityRepository;
        this.departmentRepository = departmentRepository;
        this.regionRepository = regionRepository;
        this.categoryRepository = categoryRepository;
        this.profileRepository = profileRepository;
    }

    public Activity add(Activity activity, Long regionId, Long departmentId, Long cityId, Long profileId, Long categoryId) {
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
        /*Profile existingProfile = profileRepository.findById(profileId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Profile with id " +  profileId + " not found")
                );*/
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Category with id " +  categoryId + " not found")
                );
        activity.setRegion(existingRegion);
        activity.setDepartment(existingDepartment);
        activity.setCity(existingCity);
        /*activity.setProfile(existingProfile);*/
        activity.setCategory(existingCategory);

        Activity savedActivity = activityRepository.save(activity);

        /*existingProfile.getActivities().add(activity);*/
        /*profileRepository.save(existingProfile);*/
        
        return savedActivity;
    }

    @Override
    public Activity updateById(Activity activity, Long activityId) {
        Activity newActivity = findById(activityId);

        newActivity.setName(activity.getName());
        newActivity.setDate(activity.getDate());
        newActivity.setAge(activity.getAge());
        newActivity.setImgUrl(activity.getImgUrl());
        newActivity.setLink(activity.getLink());
        newActivity.setDescription(activity.getDescription());
        newActivity.setNbGuest(activity.getNbGuest());
        newActivity.setVisible(activity.isVisible());

        return activityRepository.save(newActivity);
    }

    /*public int countBookingsByActivityId(Long activityId) {
        return activityRepository.countBookingsByActivityId(activityId);
    }*/
}
