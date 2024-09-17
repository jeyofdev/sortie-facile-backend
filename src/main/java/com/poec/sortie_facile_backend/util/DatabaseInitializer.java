package com.poec.sortie_facile_backend.util;

import com.poec.sortie_facile_backend.data.all.AllDataResponse;
import com.poec.sortie_facile_backend.data.all.AllDataService;
import com.poec.sortie_facile_backend.data.all.category.CategoryDataInfo;
import com.poec.sortie_facile_backend.data.all.contact.ContactDataInfo;
import com.poec.sortie_facile_backend.data.all.profile.ProfileDataInfo;
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
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
        if (this.authUserRepository.findByEmail("admin@admin.com").isEmpty()) {
            this.createAdmin();
            this.createUsers();
        }

        this.createDatas();
    }

    private void createAdmin() {
        AuthUser admin = AuthUser.builder()
                .nickname("admin")
                .email("admin@admin.com")
                .password(passwordEncoder.encode("admin"))
                .role("ROLE_" + Role.ADMIN)
                .build();

        this.authUserRepository.save(admin);
    }

    private void createUsers() {
        AuthUser user1 = AuthUser.builder()
                .nickname("user")
                .email("user@user.com")
                .password(passwordEncoder.encode("user"))
                .role("ROLE_" + Role.USER)
                .build();

        this.authUserRepository.save(user1);
    }

    private void createDatas() throws IOException {
        ListLocationDataResponse locationDataList = locationDataService.getAllDatas();
        AllDataResponse allDataList = allDataService.getAllDatas();

        this.createContacts(allDataList);
        this.createRegions(locationDataList);
        this.createDepartments(locationDataList);
        this.createCities(locationDataList);
        this.createCategories(allDataList);
        this.createProfiles(allDataList);
       /* this.createActivities();*/
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
                        profile.getBookingIds()
                ))
                .toList();

        for (ProfileDataInfo profile : profileList) {
            Region region = regionRepository.findById(profile.getRegionId()).orElse(null);
            Department department = departmentRepository.findById(profile.getDepartmentId()).orElse(null);
            City city = cityRepository.findById(profile.getCityId()).orElse(null);

            Profile currentProfile = profileMapper.mapToEntity(new SaveProfileDTO(
                            profile.getFirstname(),
                            profile.getLastname(),
                            profile.getStreetNumber(),
                            profile.getStreet(),
                            profile.getZipCode(),
                            profile.getDescription(),
                            profile.getAvatar(),
                            profile.getPhone(),
                            profile.getDateOfBirth(),
                            profile.getCategoryIds(),
                            null,
                            null,
                            null
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

    private void createActivities() {
        List<SaveActivityDTO> saveActivityList = Arrays.asList(
                new SaveActivityDTO(
                        "Randonnée en montagne",
                        25,
                        "https://images.unsplash.com/photo-1469395013119-ca3b424d83e5?q=80&w=1473&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        null,
                        "Découvrez les paysages magnifiques des montagnes en participant à cette randonnée organisée pour les amateurs de nature.",
                        10,
                        true,
                        new ArrayList<>(),
                        null,
                        null,
                        null
                ),
                new SaveActivityDTO(
                        "Projection de film en plein air",
                        25,
                        "https://images.unsplash.com/photo-1608170825938-a8ea0305d46c?q=80&w=1325&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        null,
                        "Venez profiter d'une soirée cinéma sous les étoiles avec la projection d'un classique du cinéma.",
                        50,
                        true,
                        new ArrayList<>(),
                        null,
                        null,
                        null
                ),
                new SaveActivityDTO(
                        "Visite guidée d'un musée",
                        25,
                        "https://images.unsplash.com/photo-1518998053901-5348d3961a04?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        null,
                        "Découvrez les trésors artistiques du musée avec une visite guidée interactive et immersive.",
                        15,
                        true,
                        new ArrayList<>(),
                        null,
                        null,
                        null
                ),
                new SaveActivityDTO(
                        "Match caritatif de football",
                        25,
                        "https://images.unsplash.com/photo-1494177310973-4841f7d5a882?q=80&w=1471&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        null,
                        "Participez à un tournoi de football pour une bonne cause.",
                        22,
                        true,
                        new ArrayList<>(),
                        null,
                        null,
                        null
                )
        );

        for (SaveActivityDTO saveActivity : saveActivityList) {
            Activity activity = activityMapper.mapToEntity(saveActivity);
            activityRepository.save(activity);
        }
    }

}
