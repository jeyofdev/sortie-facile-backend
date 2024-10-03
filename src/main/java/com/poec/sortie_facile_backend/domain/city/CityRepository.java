package com.poec.sortie_facile_backend.domain.city;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByDepartmentId(Long departmentId);
}
