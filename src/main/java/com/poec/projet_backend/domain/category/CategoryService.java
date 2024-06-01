package com.poec.projet_backend.domain.category;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

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

    public Category add(Category booking) {
        return repository.save(booking);
    }

    public Category update(Category category, Long id) {
        Category newCategory = getById(id);
        newCategory.setActivities(category.getActivities());
        newCategory.setTitle(category.getTitle());
        newCategory.setProfiles(category.getProfiles());

        return repository.save(newCategory);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
