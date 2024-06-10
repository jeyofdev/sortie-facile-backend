package com.poec.projet_backend.domain.activity;

import com.poec.projet_backend.domain.booking.BookingRepository;
import com.poec.projet_backend.domain.category.CategoryRepository;
import com.poec.projet_backend.domain.city.CityRepository;
import com.poec.projet_backend.domain.department.Department;
import com.poec.projet_backend.domain.department.DepartmentRepository;
import com.poec.projet_backend.domain.region.RegionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ActivityService {

    @Autowired
    private ActivityRepository repository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Activity> getAll() {
        return repository.findAll();
    }

    public Activity getById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(id + " not found")
                );
    }

    public Activity add(ActivityFrontToBackDTO activityFrontToBackDTO) {
        Activity newActivity = ActivityFrontToBackDTO.mapToEntity(activityFrontToBackDTO);
//        newActivity.setCity(cityRepository.findById(activityFrontToBackDTO.cityId()));
//        newActivity.setDepartment(departmentRepository.findById(activityFrontToBackDTO.departmentId()));
//        newActivity.setRegion(regionRepository.findById(activityFrontToBackDTO.regionId()));
        newActivity.setCategories(categoryRepository.findAllById(activityFrontToBackDTO.categoryIds()));
//        newActivity.setBookings(bookingRepository.findAllById(activityFrontToBackDTO.bookingIds()));
        return repository.save(newActivity);
    }

    public Activity update(Activity activity, Long id) {
        Activity newActivity = getById(id);
        newActivity.setName(activity.getName());
        newActivity.setDate(activity.getDate());
        newActivity.setAge(activity.getAge());
        newActivity.setImgUrl(activity.getImgUrl());
        newActivity.setLink(activity.getLink());
        newActivity.setDescription(activity.getDescription());
        newActivity.setNbGuest(activity.getNbGuest());
        newActivity.setHour(activity.getHour());
        newActivity.setVisible(activity.isVisible());
        newActivity.setBookings(activity.getBookings());
        newActivity.setCategories(activity.getCategories());
//        newActivity.setCity(activity.getCity());
//        newActivity.setDepartment(activity.getDepartment());
//        newActivity.setRegion(activity.getRegion());

        return repository.save(newActivity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
