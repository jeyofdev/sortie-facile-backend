package com.poec.sortie_facile_backend.domain.department;

import com.poec.sortie_facile_backend.core.abstracts.AbstractDomainService;
import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.city.City;
import com.poec.sortie_facile_backend.domain.city.CityRepository;
import com.poec.sortie_facile_backend.domain.profile.Profile;
import com.poec.sortie_facile_backend.domain.region.Region;
import com.poec.sortie_facile_backend.domain.region.RegionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService extends AbstractDomainService<Department> {

    private final DepartmentRepository departmentRepository;
    private final RegionRepository regionRepository;
    private final CityRepository cityRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, RegionRepository regionRepository, CityRepository cityRepository) {
        super(departmentRepository, "department");
        this.departmentRepository = departmentRepository;
        this.regionRepository = regionRepository;
        this.cityRepository = cityRepository;
    }

    public Department add(Department department, Long RegionId) {
        Region newRegion = regionRepository.findById(RegionId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Region with id " + RegionId + " not found")
                );

        department.setRegion(newRegion);

        return departmentRepository.save(department);
    }

    @Override
    public Department updateById(Department department, Long departmentId) {
        Department existingDepartment = findById(departmentId);
        existingDepartment.setName(department.getName());

        if (department.getRegion() != null) {
            Region region = regionRepository.findById(department.getRegion().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Region with id " + department.getRegion().getId() + " not found"));
            existingDepartment.setRegion(region);
        }

        return departmentRepository.save(existingDepartment);
    }

    @Override
    public void deleteById(Long departmentId) {
        Department department = findById(departmentId);

        for (Profile profile : department.getProfiles()) {
            profile.setDepartment(null);
        }

        for (Activity activity : department.getActivities()) {
            activity.setDepartment(null);
        }

        for (City city : department.getCities()) {
            for (Activity activity : department.getActivities()) {
                activity.setCity(null);
            }

            for (Profile profile : city.getProfiles()) {
                profile.setCity(null);
            }

            city.setDepartment(null);
            cityRepository.delete(city);
        }

        department.getProfiles().clear();
        department.setRegion(null);
        repository.deleteById(departmentId);
    }
}
