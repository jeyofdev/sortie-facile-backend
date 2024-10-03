package com.poec.sortie_facile_backend.domain.department;

import com.poec.sortie_facile_backend.domain.department.dto.DepartmentDTO;
import com.poec.sortie_facile_backend.domain.department.dto.SaveDepartmentDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
        String roles  = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

        List<Department> departmentList = departmentService.findAll();
        List<DepartmentDTO> departmentDTOS = departmentList.stream().map(department -> departmentMapper.mapFromEntity(department, false, roles.equals("[ROLE_ADMIN]"))).toList();

        return new ResponseEntity<>(departmentDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<DepartmentDTO> getById(@PathVariable("id") Long departmentId) {
        String roles  = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

        Department department = departmentService.findById(departmentId);
        DepartmentDTO departmentDTO = departmentMapper.mapFromEntity(department, false, roles.equals("[ROLE_ADMIN]"));

        return new ResponseEntity<>(departmentDTO, HttpStatus.FOUND);
    }

    @GetMapping(ALL + REGION + ID)
    public ResponseEntity<List<DepartmentDTO>> getByRegion(@PathVariable("id") Long regionId) {
        String roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

        List<Department> departmentList = departmentService.findByRegion(regionId);
        List<DepartmentDTO> departmentDTOS = departmentList.stream().map(department -> departmentMapper.mapFromEntity(department, false, roles.equals("[ROLE_ADMIN]"))).toList();

        return new ResponseEntity<>(departmentDTOS, HttpStatus.FOUND);
    }

    @PostMapping(ADD + REGION + ID)
    public ResponseEntity<DepartmentDTO> add(
            @Valid @RequestBody SaveDepartmentDTO saveDepartmentDTO,
            @PathVariable("id") Long regionId
    ) {
        Department department = departmentMapper.mapToEntity(saveDepartmentDTO);
        Department newDepartment = departmentService.add(department, regionId);
        DepartmentDTO newDepartmentDTO = departmentMapper.mapFromEntity(newDepartment, false, false);

        return new ResponseEntity<>(newDepartmentDTO, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<DepartmentDTO> updateById(
            @Valid @RequestBody SaveDepartmentDTO saveDepartmentDTO,
            @PathVariable("id") Long departmentId
    ) {
        Department department = departmentMapper.mapToEntity(saveDepartmentDTO);
        Department updatedDepartment = departmentService.updateById(department, departmentId);
        DepartmentDTO updatedDepartmentDTO = departmentMapper.mapFromEntity(updatedDepartment, false, false);

        return new ResponseEntity<>(updatedDepartmentDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long departmentId) {
        departmentService.deleteById(departmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
