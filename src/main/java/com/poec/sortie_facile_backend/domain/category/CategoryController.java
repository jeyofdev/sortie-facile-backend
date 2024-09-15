package com.poec.sortie_facile_backend.domain.category;

import com.poec.sortie_facile_backend.domain.category.dto.CategoryDTO;
import com.poec.sortie_facile_backend.domain.category.dto.SaveCategoryDTO;
import jakarta.validation.Valid;
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

    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping(ALL)
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<Category> categoryList = categoryService.findAll();
        List<CategoryDTO> categoryDTOS = categoryList.stream().map(categoryMapper::mapFromEntity).toList();

        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<CategoryDTO> getById(@PathVariable("id") Long categoryId) {
        Category category = categoryService.findById(categoryId);
        CategoryDTO categoryDTO = categoryMapper.mapFromEntity(category);

        return new ResponseEntity<>(categoryDTO, HttpStatus.FOUND);
    }

    @PostMapping(ADD)
    public ResponseEntity<CategoryDTO> add(@Valid @RequestBody SaveCategoryDTO saveCategoryDTO) {
        Category category = categoryMapper.mapToEntity(saveCategoryDTO);
        Category newCategory = categoryService.add(category);
        CategoryDTO newCategoryDTO = categoryMapper.mapFromEntity(newCategory);

        return new ResponseEntity<>(newCategoryDTO, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<CategoryDTO> updateById(
            @Valid @RequestBody SaveCategoryDTO saveCategoryDTO,
            @PathVariable("id") Long categoryId
    ) {
        Category category = categoryMapper.mapToEntity(saveCategoryDTO);
        Category updatedCategory = categoryService.updateById(category, categoryId);
        CategoryDTO updatedCategoryDTO = categoryMapper.mapFromEntity(updatedCategory);

        return new ResponseEntity<>(updatedCategoryDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long categoryId) {
        categoryService.deleteById(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

