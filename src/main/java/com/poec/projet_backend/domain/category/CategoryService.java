package com.poec.projet_backend.domain.category;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Category getById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(id + " not found")
                );
    }

    public Category add(Category category) {
        return repository.save(category);
    }

    public Category update(Category category, Long id) {
        Category newCategory = getById(id);
        newCategory.setTitle(category.getTitle());
        newCategory.setActivities(category.getActivities());
        newCategory.setProfiles(category.getProfiles());

        return repository.save(newCategory);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
