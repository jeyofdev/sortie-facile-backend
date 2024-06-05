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
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<Category> categories = service.getAll();
        List<CategoryDTO> categoryDTOS = categories.stream().map(CategoryDTO::mapFromEntity).toList();
        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public CategoryDTO getById(@PathVariable Long id) {
        Category newCategory = service.getById(id);
        CategoryDTO categoryDTO = CategoryDTO.mapFromEntity(newCategory);
        return categoryDTO;
    }

    @PostMapping("/add")
    public CategoryDTO add(@RequestBody Category category) {
        Category newCategory = service.add(category);
        CategoryDTO categoryDTO = CategoryDTO.mapFromEntity(newCategory);
        return categoryDTO;
    }

    @PutMapping("update/{id}")
    public CategoryDTO update(@RequestBody Category category, @PathVariable Long id) {
        Category newCategory = service.update(category, id);
        CategoryDTO categoryDTO = CategoryDTO.mapFromEntity(newCategory);
        return categoryDTO;
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
