package com.poec.sortie_facile_backend.domain.profile;

import com.poec.sortie_facile_backend.core.abstracts.AbstractDomainService;
import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.category.Category;
import com.poec.sortie_facile_backend.domain.category.CategoryRepository;
import com.poec.sortie_facile_backend.domain.city.City;
import com.poec.sortie_facile_backend.domain.city.CityRepository;
import com.poec.sortie_facile_backend.domain.department.Department;
import com.poec.sortie_facile_backend.domain.department.DepartmentRepository;
import com.poec.sortie_facile_backend.domain.region.Region;
import com.poec.sortie_facile_backend.domain.region.RegionRepository;
import com.poec.sortie_facile_backend.auth_user.AuthUser;
import com.poec.sortie_facile_backend.auth_user.AuthUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService extends AbstractDomainService<Profile> {

    private final ProfileRepository profileRepository;
    private final CategoryRepository categoryRepository;
    private final AuthUserRepository authUserRepository;
    private final CityRepository cityRepository;
    private final RegionRepository regionRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, CategoryRepository categoryRepository, AuthUserRepository authUserRepository, CityRepository cityRepository, RegionRepository regionRepository, DepartmentRepository departmentRepository) {
        super(profileRepository, "profile");
        this.profileRepository = profileRepository;
        this.categoryRepository = categoryRepository;
        this.authUserRepository = authUserRepository;
        this.cityRepository = cityRepository;
        this.regionRepository = regionRepository;
        this.departmentRepository = departmentRepository;
    }

    public Profile add(Profile profile, Long regionId, Long departmentId, Long cityId, Long userId) {
        Region newRegion = regionRepository.findById(regionId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Region with id " + regionId + " not found")
                );
        Department newDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Department with id " + departmentId + " not found")
                );
        City newCity = cityRepository.findById(cityId)
                .orElseThrow(
                        () -> new EntityNotFoundException("City with id " + cityId + " not found")
                );
        List<Category> categoryList = categoryRepository.findAllById(profile.getCategoryList().stream().map(Category::getId).toList());

        AuthUser newUser = authUserRepository.findById(userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("User with id " + userId + " not found")
                );

        profile.setRegion(newRegion);
        profile.setDepartment(newDepartment);
        profile.setCity(newCity);
        profile.setCategoryList(categoryList);
        profile.setUser(newUser);

        return profileRepository.save(profile);
    }

    @Override
    public Profile updateById(Profile profile, Long profileId) {
        Profile existingProfile = findById(profileId);

        existingProfile.setFirstname(profile.getFirstname());
        existingProfile.setLastname(profile.getLastname());
        existingProfile.setStreetNumber(profile.getStreetNumber());
        existingProfile.setStreet(profile.getStreet());
        existingProfile.setPostalCode(profile.getPostalCode());
        existingProfile.setDescription(profile.getDescription());
        existingProfile.setAvatar(profile.getAvatar());
        existingProfile.setPhone(profile.getPhone());
        existingProfile.setDateOfBirth(profile.getDateOfBirth());

        // update region id
        if (profile.getRegion() != null) {
            Region region = regionRepository.findById(profile.getRegion().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Region with id " + profile.getRegion().getId() + " not found"));
            existingProfile.setRegion(region);
        }

        // update department id
        if (profile.getDepartment() != null) {
            Department department = departmentRepository.findById(profile.getDepartment().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Department with id " + profile.getDepartment().getId() + " not found"));
            existingProfile.setDepartment(department);
        }

        // update city id
        if (profile.getCity() != null) {
            City city = cityRepository.findById(profile.getCity().getId())
                    .orElseThrow(() -> new EntityNotFoundException("City with id " + profile.getCity().getId() + " not found"));
            existingProfile.setCity(city);
        }

        // update category ids list
        if (profile.getCategoryList() != null) {
            List<Category> newCategories = categoryRepository.findAllById(profile.getCategoryList().stream()
                    .map(Category::getId)
                    .toList()
            );

            existingProfile.setCategoryList(newCategories);
        }

        return profileRepository.save(existingProfile);
    }

    @Override
    public void deleteById(Long profileId) {
        Profile profile = findById(profileId);

        for (Activity activity : profile.getActivityList()) {
            activity.setProfile(null);
        }

        for (Category category : profile.getCategoryList()) {
            category.setProfileList(null);
        }

        repository.deleteById(profileId);
    }
}
