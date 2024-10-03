package com.poec.sortie_facile_backend.domain.department;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String departmentName);

    List<Department> findByRegionId(Long regionId);
}
