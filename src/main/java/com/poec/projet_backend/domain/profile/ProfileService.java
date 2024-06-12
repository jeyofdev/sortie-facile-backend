package com.poec.projet_backend.domain.profile;

import com.poec.projet_backend.domain.booking.Booking;
import com.poec.projet_backend.domain.booking.BookingRepository;
import com.poec.projet_backend.domain.category.CategoryRepository;
import com.poec.projet_backend.domain.city.City;
import com.poec.projet_backend.domain.city.CityRepository;
import com.poec.projet_backend.domain.department.Department;
import com.poec.projet_backend.domain.department.DepartmentRepository;
import com.poec.projet_backend.domain.region.Region;
import com.poec.projet_backend.domain.region.RegionRepository;
import com.poec.projet_backend.user_app.UserApp;
import com.poec.projet_backend.user_app.UserAppRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserAppRepository userAppRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private DepartmentRepository departmentRepository;


    public List<Profile> getAll() {
        return repository.findAll();
    }

    public Profile getById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(id + " not found")
                );
    }

    public Profile add(Profile profile, Long regionId, Long departmentId, Long cityId, Long userId) {
        Region newRegion = regionRepository.findById(regionId)
                .orElseThrow(
                        () -> new EntityNotFoundException(regionId + " not found")
                );
        Department newDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(
                        () -> new EntityNotFoundException(departmentId + " not found")
                );
        City newCity = cityRepository.findById(cityId)
                .orElseThrow(
                        () -> new EntityNotFoundException(cityId + " not found")
                );
        UserApp newUser = userAppRepository.findById(userId)
                .orElseThrow(
                        () -> new EntityNotFoundException(userId + " not found")
                );
        profile.setRegion(newRegion);
        profile.setDepartment(newDepartment);
        profile.setCity(newCity);
        profile.setUser(newUser);

        return repository.save(profile);
    }

    public Profile update(Profile profile, Long id) {
        Profile newProfile = getById(id);
        newProfile.setFirstname(profile.getFirstname());
        newProfile.setLastname(profile.getLastname());
        newProfile.setStreetNumber(profile.getStreetNumber());
        newProfile.setStreet(profile.getStreet());
        newProfile.setPostalCode(profile.getPostalCode());
        newProfile.setDescription(profile.getDescription());
        newProfile.setAvatar(profile.getAvatar());
        newProfile.setPhone(profile.getPhone());
        newProfile.setDateOfBirth(profile.getDateOfBirth());
        newProfile.setCategories(profile.getCategories());
        newProfile.setUser(profile.getUser());


        return repository.save(newProfile);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
