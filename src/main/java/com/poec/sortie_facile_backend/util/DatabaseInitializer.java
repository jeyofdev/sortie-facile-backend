package com.poec.sortie_facile_backend.util;

import com.poec.sortie_facile_backend.data.location.model.ListLocationResponse;
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
    private final LocationDataService locationService;

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
        ListLocationResponse locationDataList = locationService.getAllDatas();

        this.createMessageEmails();
        this.createRegions(locationDataList);
        this.createDepartments(locationDataList);
        this.createCities(locationDataList);
        this.createCategories();
        this.createProfiles();
        this.createActivities();
    }

    private void createMessageEmails() {
        List<SaveContactDTO> saveEmailMessageList = Arrays.asList(
                new SaveContactDTO("Renseignement sur une activité", "johndoe@test.com", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In in felis quis odio elementum bibendum a id tellus. Integer ultricies vel mauris eu pretium. Donec efficitur felis quis tincidunt vulputate. Aliquam et odio efficitur, bibendum elit sed, ullamcorper tellus. Pellentesque at sapien vitae diam euismod viverra eu vel nisi.", false),
                new SaveContactDTO("Annulation de réservation", "janedoe@test.com", "Sed at rhoncus sem. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Integer maximus odio id sem tristique efficitur. Sed efficitur, tortor sed tempus aliquet, quam orci varius tellus, eget vestibulum urna justo quis sapien. Phasellus gravida consequat pharetra.", false)
        );

        for (SaveContactDTO saveEmailMessage : saveEmailMessageList) {
            Contact emailMessage = contactMapper.mapToEntity(saveEmailMessage);
            contactRepository.save(emailMessage);
        }
    }

    private void createRegions(ListLocationResponse locationDataList) {
        List<LocationRegionInfo> regionList = locationDataList.getLocationData().stream()
                .map(location -> new LocationRegionInfo(location.getRegionName()))
                .distinct()
                .toList();

        for (LocationRegionInfo region : regionList) {
            Region currentRegion = regionMapper.mapToEntity(new SaveRegionDTO(region.getName()));
            regionRepository.save(currentRegion);
        }
    }

    private void createDepartments(ListLocationResponse locationDataList) {
        List<LocationDepartmentInfo> departmentList = locationDataList.getLocationData().stream()
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

    private void createCities(ListLocationResponse locationDataList) {
        List<LocationCityInfo> cityList = locationDataList.getLocationData().stream()
                .map(location -> new LocationCityInfo(location.getLabel(), location.getZipCode()))
                .distinct()
                .limit(100)
                .toList();

        List<SaveCityDTO> saveCityList = new ArrayList<>();
        for (LocationCityInfo city : cityList) {
            saveCityList.add(new SaveCityDTO(city.getLabel(), city.getZipCode(), null));
        }

        for (SaveCityDTO saveCity : saveCityList) {
            City department = cityMapper.mapToEntity(saveCity);
            cityRepository.save(department);
        }
    }

    private void createCategories() {
        List<SaveCategoryDTO> saveCategoryList = Arrays.asList(
                new SaveCategoryDTO("Sport", "https://images.unsplash.com/photo-1461896836934-ffe607ba8211?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8c3BvcnR8ZW58MHx8MHx8fDA%3D"),
                new SaveCategoryDTO("Cinéma", "https://images.unsplash.com/photo-1604061986761-d9d0cc41b0d1?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8VGFibGUlMjBCYXNzZXxlbnwwfHwwfHx8MA%3D%3D"),
                new SaveCategoryDTO("Culture", "https://plus.unsplash.com/premium_photo-1661407582641-9ce38a3c8402?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8Q2FuYXAlQzMlQTl8ZW58MHx8MHx8fDA%3D"),
                new SaveCategoryDTO("Musique", "https://images.unsplash.com/photo-1595428774223-ef52624120d2?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8QXJtb2lyZXxlbnwwfHwwfHx8MA%3D%3D"),
                new SaveCategoryDTO("Jeux vidéos", "https://images.unsplash.com/photo-1586753513812-462ed2a82584?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mjh8fCVDMyVBOWNsYWlyYWdlJTIwbGVkfGVufDB8fDB8fHww")
        );

        for (SaveCategoryDTO saveCategory : saveCategoryList) {
            Category category = categoryMapper.mapToEntity(saveCategory);
            categoryRepository.save(category);
        }
    }

    private void createProfiles() {
        List<SaveProfileDTO> saveProfileList = Arrays.asList(
                new SaveProfileDTO(
                        "Alice",
                        "Doe",
                        "12",
                        "Place de la Victoire",
                        "33000",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse faucibus interdum urna, vel sagittis lectus tristique at.",
                        "https://images.unsplash.com/photo-1543610892-0b1f7e6d8ac1?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8YXZhdGFyfGVufDB8fDB8fHww",
                        "0122334455",
                        LocalDate.of(2000, 5, 10),
                        new ArrayList<>(),
                        null,
                        null,
                        null
                ),
                new SaveProfileDTO(
                        "Sophie",
                        "Doe",
                        "45",
                        "Avenue de la république",
                        "75008",
                        "Curabitur vehicula, purus a fringilla dapibus, arcu magna pharetra augue, vel gravida risus turpis sit amet lectus.",
                        "https://images.unsplash.com/photo-1544005313-94ddf0286df2?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8YXZhdGFyfGVufDB8fDB8fHww",
                        "0145622333",
                        LocalDate.of(2002, 1, 21),
                        new ArrayList<>(),
                        null,
                        null,
                        null
                )
        );

        for (SaveProfileDTO saveProfile : saveProfileList) {
            Profile profile = profileMapper.mapToEntity(saveProfile);
            profileRepository.save(profile);
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
