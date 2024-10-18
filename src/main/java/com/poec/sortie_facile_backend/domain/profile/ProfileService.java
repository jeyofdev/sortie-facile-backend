package com.poec.sortie_facile_backend.domain.profile;

import com.poec.sortie_facile_backend.core.abstracts.AbstractDomainService;
import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.category.Category;
import com.poec.sortie_facile_backend.domain.category.CategoryRepository;
import com.poec.sortie_facile_backend.domain.category.CategoryService;
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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProfileService extends AbstractDomainService<Profile> {

    private final ProfileRepository profileRepository;
    private final CategoryRepository categoryRepository;
    private final AuthUserRepository authUserRepository;
    private final CityRepository cityRepository;
    private final RegionRepository regionRepository;
    private final DepartmentRepository departmentRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, CategoryRepository categoryRepository, AuthUserRepository authUserRepository, CityRepository cityRepository, RegionRepository regionRepository, DepartmentRepository departmentRepository, CategoryService categoryService) {
        super(profileRepository, "profile");
        this.profileRepository = profileRepository;
        this.categoryRepository = categoryRepository;
        this.authUserRepository = authUserRepository;
        this.cityRepository = cityRepository;
        this.regionRepository = regionRepository;
        this.departmentRepository = departmentRepository;
        this.categoryService = categoryService;
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

        AuthUser authUser = authUserRepository.findById(userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("User with id " + userId + " not found")
                );

        profile.setRegion(newRegion);
        profile.setDepartment(newDepartment);
        profile.setCity(newCity);
        profile.setCategoryList(categoryList);
        profile.setUser(authUser);

        return profileRepository.save(profile);
    }

    @Override
    public Profile findById(Long profileId) {
        String roles  = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        Long userId = authUserRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get().getId();

        if((roles.equals("[ROLE_ADMIN]")) || (roles.equals("[ROLE_USER]") && Objects.equals(profileId, userId))) {
            return repository.findById(profileId).orElseThrow(
                    () -> new EntityNotFoundException("Profile with id " + profileId + " cannot be found"));
        } else {
            throw new AccessDeniedException("You are not authorized to access this resource");
        }
    }

    @Override
    public Profile updateById(Profile profile, Long profileId) {
        String roles  = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        Long userId = authUserRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get().getId();

        if((roles.equals("[ROLE_ADMIN]")) || (roles.equals("[ROLE_USER]") && Objects.equals(profileId, userId))) {
            Profile existingProfile = findById(profileId);

            existingProfile.setFirstname(profile.getFirstname());
            existingProfile.setLastname(profile.getLastname());
            existingProfile.setStreetNumber(profile.getStreetNumber());
            existingProfile.setStreet(profile.getStreet());
            existingProfile.setZipCode(profile.getZipCode());
            existingProfile.setDescription(profile.getDescription());
            existingProfile.setAvatar(profile.getAvatar());
            existingProfile.setPhone(profile.getPhone());
            existingProfile.setDateOfBirth(profile.getDateOfBirth());
            existingProfile.setTwitter(profile.getTwitter());
            existingProfile.setInstagram(profile.getInstagram());
            existingProfile.setFacebook(profile.getFacebook());

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
            if (!profile.getCategoryList().isEmpty()) {
                List<Category> newCategories = categoryRepository.findAllById(profile.getCategoryList().stream()
                        .map(Category::getId)
                        .toList()
                );

                existingProfile.setCategoryList(newCategories);
            }

            return profileRepository.save(existingProfile);
        } else {
            throw new AccessDeniedException("You are not authorized to update this resource");
        }
    }

    @Override
    public void deleteById(Long profileId) {
        String roles  = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        Long userId = authUserRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get().getId();

        if((roles.equals("[ROLE_ADMIN]")) || (roles.equals("[ROLE_USER]") && Objects.equals(profileId, userId))) {
            Profile profile = findById(profileId);

            for (Activity activity : profile.getActivityList()) {
                activity.setProfile(null);
            }

            for (Category category : profile.getCategoryList()) {
                category.setProfileList(null);
            }

            repository.deleteById(profileId);
        } else {
            throw new AccessDeniedException("You are not authorized to delete this resource");
        }
    }

    public Profile addCategoryToProfile(Long profileId, Long categoryId) {
        String roles  = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

        Long userId = authUserRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new AccessDeniedException("You are not authorized to access this resource.")).getId();


        if (roles.equals("[ROLE_ADMIN]") || (roles.equals("[ROLE_USER]") && Objects.equals(profileId, userId))) {
            Profile profile = findById(profileId);
            Category category = categoryService.findById(categoryId);

            if (profile.getCategoryList().contains(category)) {
                throw new IllegalArgumentException("The category with the id " + categoryId + " is already associated with the profile with the id " + profileId + ".");
            }

            profile.getCategoryList().add(category);
            return profileRepository.save(profile);
        }

        throw new AccessDeniedException("You are not authorized to access this resource.");
    }

    public Profile removeCategoryToProfile(Long profileId, Long categoryId) {
        String roles  = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        Long userId = authUserRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get().getId();

        if((roles.equals("[ROLE_ADMIN]")) || (roles.equals("[ROLE_USER]") && Objects.equals(profileId, userId))) {
            Profile profile = findById(profileId);
            Category category = categoryService.findById(categoryId);

            if (!profile.getCategoryList().contains(category)) {
                throw new IllegalArgumentException("The category with the id " + categoryId + " is not associated with the profile with the id " + profileId + ".");
            }

            profile.getCategoryList().remove(category);
            return profileRepository.save(profile);
        } else {
            throw new AccessDeniedException("You are not authorized to access this resource");
        }
    }
}
