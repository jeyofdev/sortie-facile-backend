package com.poec.projet_backend.domain.department;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.poec.projet_backend.util.Route.*;

@RestController
@RequestMapping(BASE_URL + DEPARTMENT)
@RequiredArgsConstructor
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping(ALL)
    public ResponseEntity<List<DepartmentDTO>> getAll() {
        List<Department> departments = service.getAll();
        List<DepartmentDTO> departmentDTOS = departments.stream().map(DepartmentDTO::mapFromEntity).collect(Collectors.toList());
        return new ResponseEntity<>(departmentDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<DepartmentDTO> getById(@PathVariable Long id) {
        Department newDepartment = service.getById(id);
        DepartmentDTO departmentDTO = DepartmentDTO.mapFromEntity(newDepartment);
        return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
    }

    @PostMapping(ADD + REGION + ID) // api/v1/department/add/region/{id}
    public ResponseEntity<DepartmentDTO> add(@RequestBody Department department, @PathVariable Long id) {
        Department newDepartment = service.add(department, id);
        DepartmentDTO departmentDTO = DepartmentDTO.mapFromEntity(newDepartment);
        return new ResponseEntity<>(departmentDTO, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<DepartmentDTO> update(@RequestBody Department department, @PathVariable Long id) {
        Department newDepartment = service.update(department, id);
        DepartmentDTO departmentDTO = DepartmentDTO.mapFromEntity(newDepartment);
        return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
