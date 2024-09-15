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

        return regionRepository.save(newRegion);
    }

    @Override
    public void deleteById(Long regionId) {
        Region region = findById(regionId);

        for (Profile profile : region.getProfileList()) {
            profile.setRegion(null);
        }

        for (Activity activity : region.getActivityList()) {
            activity.setRegion(null);
        }

        for (Department department : region.getDepartmentList()) {
            for (Activity activity : department.getActivityList()) {
                activity.setDepartment(null);
            }

            for (City city : department.getCityList()) {
                for (Activity activity : city.getActivityList()) {
                    activity.setCity(null);
                }

                for (Profile profile : department.getProfileList()) {
                    profile.setCity(null);
                }

                city.setDepartment(null);
            }

            for (Profile profile : department.getProfileList()) {
                profile.setDepartment(null);
            }

            department.setRegion(null);
            departmentRepository.delete(department);
        }

        repository.deleteById(regionId);
    }
}

