package com.poec.sortie_facile_backend.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.poec.sortie_facile_backend.core.constants.RouteConstants.*;

@RestController
@RequestMapping(BASE_URL + CATEGORY)
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(ALL)
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<Category> categories = categoryService.findAll();
        List<CategoryDTO> categoryDTOS = categories.stream().map(CategoryDTO::mapFromEntity).toList();
        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<CategoryDTO> getById(@PathVariable Long id) {
        Category newCategory = categoryService.findById(id);
        CategoryDTO categoryDTO = CategoryDTO.mapFromEntity(newCategory);
        return new ResponseEntity<>(categoryDTO, HttpStatus.FOUND);
    }

    @PostMapping(ADD)
    public ResponseEntity<CategoryDTO> add(@RequestBody Category category) {
        Category newCategory = categoryService.add(category);
        CategoryDTO categoryDTO = CategoryDTO.mapFromEntity(newCategory);
        return new ResponseEntity<>(categoryDTO, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<CategoryDTO> update(@RequestBody Category category, @PathVariable Long id) {
        Category newCategory = categoryService.updateById(category, id);
        CategoryDTO categoryDTO = CategoryDTO.mapFromEntity(newCategory);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}

