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
        AuthUser newUser = authUserRepository.findById(userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("User with id " + userId + " not found")
                );

        profile.setRegion(newRegion);
        profile.setDepartment(newDepartment);
        profile.setCity(newCity);
        profile.setUser(newUser);

        return profileRepository.save(profile);
    }

    @Override
    public Profile updateById(Profile profile, Long profileId) {
        Profile newProfile = findById(profileId);
        newProfile.setFirstname(profile.getFirstname());
        newProfile.setLastname(profile.getLastname());
        newProfile.setStreetNumber(profile.getStreetNumber());
        newProfile.setStreet(profile.getStreet());
        newProfile.setPostalCode(profile.getPostalCode());
        newProfile.setDescription(profile.getDescription());
        newProfile.setAvatar(profile.getAvatar());
        newProfile.setPhone(profile.getPhone());
        newProfile.setDateOfBirth(profile.getDateOfBirth());

        return profileRepository.save(newProfile);
    }

    @Override
    public void deleteById(Long profileId) {
        Profile profile = findById(profileId);

        for (Activity activity : profile.getActivities()) {
            activity.setProfile(null);
        }

        repository.deleteById(profileId);
    }

    public Profile updateCategoryInProfile(Long profileId, List<Long> categoryIds) {
        Profile newProfile = findById(profileId);
        List<Category> categories = categoryRepository.findAllById(categoryIds);

        /*newProfile.setCategories(categories);*/
        return profileRepository.save(newProfile);
    }
}
