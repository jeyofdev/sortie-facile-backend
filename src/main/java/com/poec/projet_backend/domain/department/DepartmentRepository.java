package com.poec.projet_backend.domain.department;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findById(Optional<Long> aLong);
}
