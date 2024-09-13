package com.poec.sortie_facile_backend.domain.region;

import com.poec.sortie_facile_backend.core.abstracts.AbstractDomainService;
import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.city.City;
import com.poec.sortie_facile_backend.domain.department.Department;
import com.poec.sortie_facile_backend.domain.department.DepartmentRepository;
import com.poec.sortie_facile_backend.domain.profile.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionService  extends AbstractDomainService<Region> {

    private final RegionRepository regionRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository, DepartmentRepository departmentRepository) {
        super(regionRepository, "region");
        this.regionRepository = regionRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Region updateById(Region region, Long regionId) {
        Region newRegion = findById(regionId);

        newRegion.setName(region.getName());
        newRegion.setDepartments(region.getDepartments());
        newRegion.setActivities(region.getActivities());

        return regionRepository.save(newRegion);
    }

    @Override
    public void deleteById(Long regionId) {
        Region region = findById(regionId);

        for (Profile profile : region.getProfiles()) {
            profile.setRegion(null);
        }

        for (Activity activity : region.getActivities()) {
            activity.setRegion(null);
        }

        for (Department department : region.getDepartments()) {
            for (Activity activity : department.getActivities()) {
                activity.setDepartment(null);
            }

            for (City city : department.getCities()) {
                for (Activity activity : city.getActivities()) {
                    activity.setCity(null);
                }

                for (Profile profile : department.getProfiles()) {
                    profile.setCity(null);
                }

                city.setDepartment(null);
            }

            for (Profile profile : department.getProfiles()) {
                profile.setDepartment(null);
            }

            department.setRegion(null);
            departmentRepository.delete(department);
        }

        repository.deleteById(regionId);
    }
}

