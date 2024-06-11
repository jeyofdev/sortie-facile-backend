package com.poec.projet_backend.domain.activity;

import com.poec.projet_backend.domain.city.City;
import com.poec.projet_backend.domain.city.CityRepository;
import com.poec.projet_backend.domain.department.Department;
import com.poec.projet_backend.domain.department.DepartmentRepository;
import com.poec.projet_backend.domain.region.Region;
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

    public List<Activity> getAll() {
        return repository.findAll();
    }

    public Activity getById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(id + " not found")
                );
    }

    public Activity add(Activity activity, Long regionId, Long departmentId, Long cityId) {
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
        activity.setRegion(newRegion);
        activity.setDepartment(newDepartment);
        activity.setCity(newCity);

        return repository.save(activity);
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

        return repository.save(newActivity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
