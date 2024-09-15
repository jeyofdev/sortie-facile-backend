package com.poec.sortie_facile_backend.domain.department;

import com.poec.sortie_facile_backend.domain.department.dto.DepartmentDTO;
import com.poec.sortie_facile_backend.domain.department.dto.SaveDepartmentDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.poec.sortie_facile_backend.core.constants.RouteConstants.*;

@RestController
@RequestMapping(BASE_URL + DEPARTMENT)
@RequiredArgsConstructor
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentMapper departmentMapper;

    @GetMapping(ALL)
    public ResponseEntity<List<DepartmentDTO>> getAll() {
        List<Department> departmentList = departmentService.findAll();
        List<DepartmentDTO> departmentDTOS = departmentList.stream().map(departmentMapper::mapFromEntity).toList();

        return new ResponseEntity<>(departmentDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<DepartmentDTO> getById(@PathVariable("id") Long departmentId) {
        Department department = departmentService.findById(departmentId);
        DepartmentDTO departmentDTO = departmentMapper.mapFromEntity(department);

        return new ResponseEntity<>(departmentDTO, HttpStatus.FOUND);
    }

    @PostMapping(ADD + REGION + ID)
    public ResponseEntity<DepartmentDTO> add(
            @Valid @RequestBody SaveDepartmentDTO saveDepartmentDTO,
            @PathVariable("id") Long regionId
    ) {
        Department department = departmentMapper.mapToEntity(saveDepartmentDTO);
        Department newDepartment = departmentService.add(department, regionId);
        DepartmentDTO newDepartmentDTO = departmentMapper.mapFromEntity(newDepartment);

        return new ResponseEntity<>(newDepartmentDTO, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<DepartmentDTO> updateById(
            @Valid @RequestBody SaveDepartmentDTO saveDepartmentDTO,
            @PathVariable("id") Long departmentId
    ) {
        Department department = departmentMapper.mapToEntity(saveDepartmentDTO);
        Department updatedDepartment = departmentService.updateById(department, departmentId);
        DepartmentDTO updatedDepartmentDTO = departmentMapper.mapFromEntity(updatedDepartment);

        return new ResponseEntity<>(updatedDepartmentDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long departmentId) {
        departmentService.deleteById(departmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
