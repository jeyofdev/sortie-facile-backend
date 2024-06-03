package com.poec.projet_backend.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = service.getAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {
        return new ResponseEntity<>(service.add(category), HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Category> update(@RequestBody Category category, @PathVariable Long id) {
        return new ResponseEntity<>(service.update(category, id), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
