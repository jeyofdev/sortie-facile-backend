package com.poec.sortie_facile_backend.domain.profile;


import com.poec.sortie_facile_backend.auth_user.AuthUser;
import com.poec.sortie_facile_backend.common.model.AddressFormat;
import com.poec.sortie_facile_backend.common.model.AgeFormat;
import com.poec.sortie_facile_backend.common.model.NameFormat;
import com.poec.sortie_facile_backend.common.model.YearFormat;
import com.poec.sortie_facile_backend.core.interfaces.BaseDomainMapper;
import com.poec.sortie_facile_backend.domain.activity.dto.ActivityDTO;
import com.poec.sortie_facile_backend.domain.booking.Booking;
import com.poec.sortie_facile_backend.domain.category.Category;
import com.poec.sortie_facile_backend.domain.category.CategoryMapper;
import com.poec.sortie_facile_backend.domain.category.CategoryRepository;
import com.poec.sortie_facile_backend.domain.city.City;
import com.poec.sortie_facile_backend.domain.city.dto.CityDTO;
import com.poec.sortie_facile_backend.domain.department.Department;
import com.poec.sortie_facile_backend.domain.department.dto.DepartmentDTO;
import com.poec.sortie_facile_backend.domain.profile.dto.ProfileDTO;
import com.poec.sortie_facile_backend.domain.profile.dto.SaveProfileDTO;
import com.poec.sortie_facile_backend.domain.region.Region;
import com.poec.sortie_facile_backend.domain.region.dto.RegionDTO;
import com.poec.sortie_facile_backend.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileMapper implements BaseDomainMapper<Profile, ProfileDTO, SaveProfileDTO> {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public ProfileMapper(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public ProfileDTO mapFromEntity(Profile profile, boolean primaryDataOnly) {
        return new ProfileDTO(
                profile.getId(),
                profile.getUser().getEmail(),
                profile.getUser().getNickname(),
                new NameFormat(profile.getFirstname(), profile.getLastname()),
                new YearFormat(profile.getDateOfBirth()),
                Helper.formatPhoneNumber(profile.getPhone()),
                new AddressFormat(
                        profile.getStreetNumber(),
                        profile.getStreet(),
                        profile.getZipCode(),
                        new RegionDTO(profile.getRegion().getId(), profile.getRegion().getName(), null, null, null),
                        new DepartmentDTO(profile.getDepartment().getId(), profile.getDepartment().getName(), profile.getDepartment().getNumber(), null, null, null, null),
                        new CityDTO(profile.getCity().getId(), profile.getCity().getName(), profile.getCity().getZipCode(), null, null, null)
                ),
                profile.getDescription(),
                profile.getAvatar(),
                profile.getActivityList() != null ? profile.getActivityList().stream().map(activity -> new ActivityDTO(
                        activity.getId(),
                        activity.getName(),
                        activity.getCreatedAt(),
                        new AgeFormat(
                            activity.getAgeMin(),
                            activity.getAgeMax()
                        ),
                        activity.getImgUrl(),
                        null,
                        activity.getDescription(),
                        activity.getNbGuest(),
                        activity.isVisible(),
                        null,
                        null,
                        null,
                        null
                )).toList() : new ArrayList<>(),
                profile.getBookingList() != null ? profile.getBookingList().stream().map(Booking::getId).toList() : new ArrayList<>(),
                profile.getCategoryList() != null ? profile.getCategoryList().stream().map(
                        category -> categoryMapper.mapFromEntity(category, true)
                ).toList() : new ArrayList<>()
        );
    }

    @Override
    public Profile mapToEntity(SaveProfileDTO saveProfileDTO) {
        Profile profile = new Profile();

        profile.setFirstname(saveProfileDTO.firstname());
        profile.setLastname(saveProfileDTO.lastname());
        profile.setDateOfBirth(saveProfileDTO.dateOfBirth());
        profile.setPhone(saveProfileDTO.phone());
        profile.setStreetNumber(saveProfileDTO.streetNumber());
        profile.setStreet(saveProfileDTO.street());
        profile.setZipCode(saveProfileDTO.zipCode());
        profile.setDescription(saveProfileDTO.description());
        profile.setAvatar(saveProfileDTO.avatar());

        if (saveProfileDTO.categoryIds() != null) {
            List<Category> categoryList = categoryRepository.findAllById(saveProfileDTO.categoryIds());
            profile.setCategoryList(categoryList);
        }

        if (saveProfileDTO.regionId() != null) {
            Region region = new Region();
            region.setId(saveProfileDTO.regionId());
            profile.setRegion(region);
        }

        if (saveProfileDTO.departmentId() != null) {
            Department department = new Department();
            department.setId(saveProfileDTO.departmentId());
            profile.setDepartment(department);
        }

        if (saveProfileDTO.cityId() != null) {
            City city = new City();
            city.setId(saveProfileDTO.cityId());
            profile.setCity(city);
        }

        if (saveProfileDTO.userId() != null) {
            AuthUser authUser = new AuthUser();
            authUser.setId(saveProfileDTO.userId());
            profile.setUser(authUser);
        }

        return profile;
    }
}
