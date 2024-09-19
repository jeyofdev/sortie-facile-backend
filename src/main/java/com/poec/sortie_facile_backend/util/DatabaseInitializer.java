package com.poec.sortie_facile_backend.util;

import com.poec.sortie_facile_backend.data.all.AllDataResponse;
import com.poec.sortie_facile_backend.data.all.AllDataService;
import com.poec.sortie_facile_backend.data.all.activity.ActivityDataInfo;
import com.poec.sortie_facile_backend.data.all.category.CategoryDataInfo;
import com.poec.sortie_facile_backend.data.all.contact.ContactDataInfo;
import com.poec.sortie_facile_backend.data.all.profile.ProfileDataInfo;
import com.poec.sortie_facile_backend.data.all.user.UserDataInfo;
import com.poec.sortie_facile_backend.data.location.model.ListLocationDataResponse;
import com.poec.sortie_facile_backend.data.location.LocationDataService;
import com.poec.sortie_facile_backend.data.location.model.LocationCityInfo;
import com.poec.sortie_facile_backend.data.location.model.LocationDepartmentInfo;
import com.poec.sortie_facile_backend.data.location.model.LocationRegionInfo;
import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.activity.ActivityMapper;
import com.poec.sortie_facile_backend.domain.activity.ActivityRepository;
import com.poec.sortie_facile_backend.domain.activity.dto.SaveActivityDTO;
import com.poec.sortie_facile_backend.domain.category.Category;
import com.poec.sortie_facile_backend.domain.category.CategoryMapper;
import com.poec.sortie_facile_backend.domain.category.CategoryRepository;
import com.poec.sortie_facile_backend.core.enums.Role;
import com.poec.sortie_facile_backend.auth_user.AuthUser;
import com.poec.sortie_facile_backend.auth_user.AuthUserRepository;
import com.poec.sortie_facile_backend.domain.category.dto.SaveCategoryDTO;
import com.poec.sortie_facile_backend.domain.city.City;
import com.poec.sortie_facile_backend.domain.city.CityMapper;
import com.poec.sortie_facile_backend.domain.city.CityRepository;
import com.poec.sortie_facile_backend.domain.city.dto.SaveCityDTO;
import com.poec.sortie_facile_backend.domain.contact.Contact;
import com.poec.sortie_facile_backend.domain.contact.ContactMapper;
import com.poec.sortie_facile_backend.domain.contact.ContactRepository;
import com.poec.sortie_facile_backend.domain.contact.dto.SaveContactDTO;
import com.poec.sortie_facile_backend.domain.department.Department;
import com.poec.sortie_facile_backend.domain.department.DepartmentMapper;
import com.poec.sortie_facile_backend.domain.department.DepartmentRepository;
import com.poec.sortie_facile_backend.domain.department.dto.SaveDepartmentDTO;
import com.poec.sortie_facile_backend.domain.profile.Profile;
import com.poec.sortie_facile_backend.domain.profile.ProfileMapper;
import com.poec.sortie_facile_backend.domain.profile.ProfileRepository;
import com.poec.sortie_facile_backend.domain.profile.dto.SaveProfileDTO;
import com.poec.sortie_facile_backend.domain.region.Region;
import com.poec.sortie_facile_backend.domain.region.RegionMapper;
import com.poec.sortie_facile_backend.domain.region.RegionRepository;
import com.poec.sortie_facile_backend.domain.region.dto.SaveRegionDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    private final CategoryRepository categoryRepository;
    private final ContactRepository contactRepository;
    private final RegionRepository regionRepository;
    private final DepartmentRepository departmentRepository;
    private final CityRepository cityRepository;
    private final ProfileRepository profileRepository;
    private final ActivityRepository activityRepository;

    private final CategoryMapper categoryMapper;
    private final ContactMapper contactMapper;
    private final RegionMapper regionMapper;
    private final DepartmentMapper departmentMapper;
    private final CityMapper cityMapper;
    private final ProfileMapper profileMapper;
    private final ActivityMapper activityMapper;

    private final LocationDataService locationDataService;
    private final AllDataService allDataService;

    @Override
    public void run(String... args) throws Exception {
        this.createDatas();
    }

    private void createDatas() throws IOException {
        ListLocationDataResponse locationDataList = locationDataService.getAllDatas();
        AllDataResponse allDataList = allDataService.getAllDatas();

        if (this.authUserRepository.findByEmail("admin@admin.com").isEmpty()) {
            this.createUsers(allDataList);
        }

        this.createContacts(allDataList);
        this.createRegions(locationDataList);
        this.createDepartments(locationDataList);
        this.createCities(locationDataList);
        this.createCategories(allDataList);
        this.createProfiles(allDataList);
      /*  this.createActivities(allDataList);*/
    }

    private void createUsers(AllDataResponse allDataList) {
        List<UserDataInfo> userList = allDataList.getUserDataList().stream()
                .map(user -> new UserDataInfo(
                        user.getNickname(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getRole().equals("ADMIN") ? Role.ADMIN : Role.USER
                ))
                .toList();

        for (UserDataInfo user : userList) {
            AuthUser newUser = AuthUser.builder()
                    .nickname(user.getNickname())
                    .email(user.getEmail())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .role("ROLE_" + user.getRole())
                    .build();

            this.authUserRepository.save(newUser);
        }
    }

    private void createContacts(AllDataResponse allDataList) {
        List<ContactDataInfo> contactList = allDataList.getContactDataList().stream()
                .map(contact -> new ContactDataInfo(
                        contact.getTitle(),
                        contact.getEmail(),
                        contact.getMessage(),
                        contact.getIsRead()
                ))
                .toList();

        for (ContactDataInfo contact : contactList) {
            Contact currentContact = contactMapper.mapToEntity(new SaveContactDTO(
                    contact.getTitle(),
                    contact.getEmail(),
                    contact.getMessage(),
                    contact.getIsRead()
            ));

            contactRepository.save(currentContact);
        }
    }

    private void createRegions(ListLocationDataResponse locationDataList) {
        List<LocationRegionInfo> regionList = locationDataList.getLocationDataList().stream()
                .map(location -> new LocationRegionInfo(location.getRegionName()))
                .distinct()
                .toList();

        for (LocationRegionInfo region : regionList) {
            Region currentRegion = regionMapper.mapToEntity(new SaveRegionDTO(region.getName()));
            regionRepository.save(currentRegion);
        }
    }

    private void createDepartments(ListLocationDataResponse locationDataList) {
        List<LocationDepartmentInfo> departmentList = locationDataList.getLocationDataList().stream()
                .map(location -> new LocationDepartmentInfo(
                        location.getDepartmentName(),
                        location.getDepartmentNumber(),
                        location.getRegionName()
                ))
                .distinct()
                .toList();

        for (LocationDepartmentInfo department : departmentList) {
            Region region = regionRepository.findByName(department.getRegionName());

            Department currentDepartment = departmentMapper.mapToEntity(
                    new SaveDepartmentDTO(department.getName(), department.getNumber(), null)
            );
            currentDepartment.setRegion(region);

            departmentRepository.save(currentDepartment);
        }
    }

    private void createCities(ListLocationDataResponse locationDataList) {
        List<LocationCityInfo> cityList = locationDataList.getLocationDataList().stream()
                .map(location -> new LocationCityInfo(location.getLabel(), location.getZipCode(), location.getDepartmentName()))
                .distinct()
                .limit(100)
                .toList();

        for (LocationCityInfo city : cityList) {
            Department department = departmentRepository.findByName(city.getDepartmentName());

            City currentCity = cityMapper.mapToEntity(
                    new SaveCityDTO(city.getLabel(), city.getZipCode(), null)
            );
            currentCity.setDepartment(department);

            cityRepository.save(currentCity);
        }
    }

    private void createCategories(AllDataResponse allDataList) {
        List<CategoryDataInfo> categoryList = allDataList.getCategoryDataList().stream()
                .map(category -> new CategoryDataInfo(category.getTitle(), category.getImgUrl()))
                .toList();

        for (CategoryDataInfo category : categoryList) {
            Category currentCategory = categoryMapper.mapToEntity(new SaveCategoryDTO(category.getTitle(), category.getImgUrl()));
            categoryRepository.save(currentCategory);
        }
    }

    private void createProfiles(AllDataResponse allDataList) {
        List<ProfileDataInfo> profileList = allDataList.getProfileDataList().stream()
                .map(profile -> new ProfileDataInfo(
                        profile.getFirstname(),
                        profile.getLastname(),
                        profile.getStreetNumber(),
                        profile.getStreet(),
                        profile.getZipCode(),
                        profile.getDescription(),
                        profile.getAvatar(),
                        profile.getPhone(),
                        LocalDate.parse(profile.getDateOfBirth()),
                        profile.getRegionId(),
                        profile.getDepartmentId(),
                        profile.getCityId(),
                        profile.getCategoryIds(),
                        profile.getActivityIds(),
                        profile.getBookingIds(),
                        profile.getProfileId()
                ))
                .toList();

        for (ProfileDataInfo profile : profileList) {
            Region region = regionRepository.findById(profile.getRegionId()).orElse(null);
            Department department = departmentRepository.findById(profile.getDepartmentId()).orElse(null);
            City city = cityRepository.findById(profile.getCityId()).orElse(null);
            AuthUser authUser = authUserRepository.findById(profile.getUserId()).orElse(null);

            Profile currentProfile = profileMapper.mapToEntity(new SaveProfileDTO(
                            profile.getFirstname(),
                            profile.getLastname(),
                            profile.getDateOfBirth(),
                            profile.getPhone(),
                            profile.getStreetNumber(),
                            profile.getStreet(),
                            profile.getZipCode(),
                            profile.getDescription(),
                            profile.getAvatar(),
                            profile.getCategoryIds(),
                            null,
                            null,
                            null,
                            profile.getUserId()
                    )
            );

            List<Category> categoryList = categoryRepository.findAllById(currentProfile.getCategoryList().stream().map(Category::getId).toList());

            currentProfile.setRegion(region);
            currentProfile.setDepartment(department);
            currentProfile.setCity(city);
            currentProfile.setCategoryList(categoryList);

            profileRepository.save(currentProfile);
        }
    }

    private void createActivities(AllDataResponse allDataList) {
        List<ActivityDataInfo> activityList = allDataList.getActivityDataList().stream()
                .map(activity -> new ActivityDataInfo(
                        activity.getName(),
                        null,
                        activity.getAge(),
                        activity.getImgUrl(),
                        activity.getLink(),
                        activity.getDescription(),
                        activity.getNbGuest(),
                        activity.getIsVisible(),
                        activity.getRegionId(),
                        activity.getDepartmentId(),
                        activity.getCityId(),
                        activity.getCategoryIds(),
                        activity.getProfileId()
                ))
                .toList();

        for (ActivityDataInfo activity : activityList) {
            Region region = regionRepository.findById(activity.getRegionId()).orElse(null);
            Department department = departmentRepository.findById(activity.getDepartmentId()).orElse(null);
            City city = cityRepository.findById(activity.getCityId()).orElse(null);
            Profile profile = profileRepository.findById(activity.getProfileId()).orElse(null);

            Activity currentActivity = activityMapper.mapToEntity(new SaveActivityDTO(
                    activity.getName(),
                    activity.getAge(),
                    activity.getImgUrl(),
                    activity.getLink(),
                    activity.getDescription(),
                    activity.getNbGuest(),
                    activity.isVisible(),
                    activity.getCategoryIds(),
                    null,
                    null,
                    null,
                    null
                    )
            );

            List<Category> categoryList = categoryRepository.findAllById(currentActivity.getCategoryList().stream().map(Category::getId).toList());

            currentActivity.setRegion(region);
            currentActivity.setDepartment(department);
            currentActivity.setCity(city);
            currentActivity.setCategoryList(categoryList);
            currentActivity.setProfile(profile);

            activityRepository.save(currentActivity);
        }
    }
}
