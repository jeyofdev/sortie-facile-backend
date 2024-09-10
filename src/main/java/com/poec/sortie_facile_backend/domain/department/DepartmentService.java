package com.poec.sortie_facile_backend.domain.department;

import com.poec.sortie_facile_backend.core.abstracts.AbstractDomainService;
import com.poec.sortie_facile_backend.domain.region.Region;
import com.poec.sortie_facile_backend.domain.region.RegionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService extends AbstractDomainService<Department> {

    private final DepartmentRepository departmentRepository;
    private final RegionRepository regionRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, RegionRepository regionRepository) {
        super(departmentRepository, "department");
        this.departmentRepository = departmentRepository;
        this.regionRepository = regionRepository;
    }

    public Department addWithRegion(Department department, Long RegionId) {
        Region newRegion = regionRepository.findById(RegionId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Region with id " + RegionId + " not found")
                );

        department.setRegion(newRegion);

        return departmentRepository.save(department);
    }

    @Override
    public Department updateById(Department department, Long departmentId) {
        Department newDepartment = findById(departmentId);
        newDepartment.setName(department.getName());

        return departmentRepository.save(newDepartment);
    }
}
