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
    public ResponseEntity<List<Department>> getAll() {
        List<Department> departments = service.getAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Department> add(@RequestBody Department department) {
        return new ResponseEntity<>(service.add(department), HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Department> update(@RequestBody Department department, @PathVariable Long id) {
        return new ResponseEntity<>(service.update(department, id), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
