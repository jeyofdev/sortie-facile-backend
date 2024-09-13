package com.poec.sortie_facile_backend.domain.profile;

import com.poec.sortie_facile_backend.auth_user.AuthUser;
import com.poec.sortie_facile_backend.core.interfaces.BaseDomainMapper;
import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.booking.Booking;
import com.poec.sortie_facile_backend.domain.category.Category;
import com.poec.sortie_facile_backend.domain.category.CategoryRepository;
import com.poec.sortie_facile_backend.domain.city.City;
import com.poec.sortie_facile_backend.domain.department.Department;
import com.poec.sortie_facile_backend.domain.profile.dto.ProfileDTO;
import com.poec.sortie_facile_backend.domain.profile.dto.ProfileUpdateCategoriesDTO;
import com.poec.sortie_facile_backend.domain.profile.dto.SaveProfileDTO;
import com.poec.sortie_facile_backend.domain.region.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileMapper implements BaseDomainMapper<Profile, ProfileDTO, SaveProfileDTO> {
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProfileMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProfileDTO mapFromEntity(Profile profile) {
        return new ProfileDTO(
                profile.getId(),
                profile.getFirstname(),
                profile.getLastname(),
                profile.getStreetNumber(),
                profile.getStreet(),
                profile.getPostalCode(),
                profile.getDescription(),
                profile.getAvatar(),
                profile.getPhone(),
                profile.getDateOfBirth(),
                profile.getActivities() != null ? profile.getActivities().stream().map(Activity::getId).toList() : new ArrayList<>(),
                Optional.ofNullable(profile.getRegion()).map(Region::getId).orElse(null),
                Optional.ofNullable(profile.getDepartment()).map(Department::getId).orElse(null),
                Optional.ofNullable(profile.getCity()).map(City::getId).orElse(null),
                Optional.ofNullable(profile.getUser()).map(AuthUser::getId).orElse(null),
                profile.getBookings() != null ? profile.getBookings().stream().map(Booking::getId).toList() : new ArrayList<>(),
                profile.getCategories() != null ? profile.getCategories().stream().map(Category::getId).toList() : new ArrayList<>()
        );
    }

    @Override
    public Profile mapToEntity(SaveProfileDTO saveProfileDTO) {
        List<Category> categoryList = categoryRepository.findAllById(saveProfileDTO.categoryIds());

        Profile profile = new Profile();

        profile.setDescription(saveProfileDTO.description());
        profile.setFirstname(saveProfileDTO.firstname());
        profile.setLastname(saveProfileDTO.lastname());
        profile.setStreetNumber(saveProfileDTO.streetNumber());
        profile.setStreet(saveProfileDTO.street());
        profile.setPostalCode(saveProfileDTO.postalCode());
        profile.setAvatar(saveProfileDTO.avatar());
        profile.setPhone(saveProfileDTO.phone());
        profile.setDateOfBirth(saveProfileDTO.dateOfBirth());
        profile.setCategories(categoryList);

        return profile;
    }

    public ProfileUpdateCategoriesDTO mapFromEntityCategory(Profile profile) {
        return new ProfileUpdateCategoriesDTO (
                /*profile.getCategories().stream().map(Category::getId).toList()*/
        );
    }
}
