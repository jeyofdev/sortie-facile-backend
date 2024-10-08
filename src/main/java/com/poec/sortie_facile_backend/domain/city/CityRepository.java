package com.poec.sortie_facile_backend.domain.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByDepartmentId(Long departmentId);

    List<City> findByZipCode(String zipCode);
}
