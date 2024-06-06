package com.poec.projet_backend.domain.department;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentDTO>> getAll() {
        List<Department> departments = service.getAll();
        List<DepartmentDTO> departmentDTOS = departments.stream().map(DepartmentDTO::mapFromEntity).toList();
        return new ResponseEntity<>(departmentDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public DepartmentDTO getById(@PathVariable Long id) {
        Department newDepartment = service.getById(id);
        DepartmentDTO departmentDTO = DepartmentDTO.mapFromEntity(newDepartment);
        return departmentDTO;
    }

    @PostMapping("/add")
    public DepartmentDTO add(@RequestBody Department department) {
        Department newDepartment = service.add(department);
        DepartmentDTO departmentDTO = DepartmentDTO.mapFromEntity(newDepartment);
        return departmentDTO;
    }

    @PutMapping("update/{id}")
    public DepartmentDTO update(@RequestBody Department department, @PathVariable Long id) {
        Department newDepartment = service.update(department, id);
        DepartmentDTO departmentDTO = DepartmentDTO.mapFromEntity(newDepartment);
        return departmentDTO;
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
