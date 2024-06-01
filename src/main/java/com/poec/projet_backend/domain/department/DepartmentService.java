package com.poec.projet_backend.domain.department;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentService {

    private DepartmentRepository repository;

    public List<Department> getAll() {
        return repository.findAll();
    }

    public Department getById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(id + " not found")
                );
    }

    public Department add(Department department) {
        return repository.save(department);
    }

    public Department update(Department department, Long id) {
        Department newDepartment = getById(id);
        newDepartment.setDepartment(department.getDepartment());

        return repository.save(newDepartment);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
