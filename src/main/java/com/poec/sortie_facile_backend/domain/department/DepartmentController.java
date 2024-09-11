package com.poec.sortie_facile_backend.domain.department;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.poec.sortie_facile_backend.core.constants.RouteConstants.*;

@RestController
@RequestMapping(BASE_URL + DEPARTMENT)
@RequiredArgsConstructor
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(ALL)
    public ResponseEntity<List<DepartmentDTO>> getAll() {
        List<Department> departments = departmentService.findAll();
        List<DepartmentDTO> departmentDTOS = departments.stream().map(DepartmentDTO::mapFromEntity).collect(Collectors.toList());
        return new ResponseEntity<>(departmentDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<DepartmentDTO> getById(@PathVariable Long id) {
        Department newDepartment = departmentService.findById(id);
        DepartmentDTO departmentDTO = DepartmentDTO.mapFromEntity(newDepartment);
        return new ResponseEntity<>(departmentDTO, HttpStatus.FOUND);
    }

    @PostMapping(ADD + REGION + ID)
    public ResponseEntity<DepartmentDTO> add(@RequestBody Department department, @PathVariable Long id) {
        Department newDepartment = departmentService.add(department, id);
        DepartmentDTO departmentDTO = DepartmentDTO.mapFromEntity(newDepartment);
        return new ResponseEntity<>(departmentDTO, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<DepartmentDTO> update(@RequestBody Department department, @PathVariable Long id) {
        Department newDepartment = departmentService.updateById(department, id);
        DepartmentDTO departmentDTO = DepartmentDTO.mapFromEntity(newDepartment);
        return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
