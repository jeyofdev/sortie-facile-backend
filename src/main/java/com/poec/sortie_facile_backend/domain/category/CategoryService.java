package com.poec.sortie_facile_backend.domain.category;

import com.poec.sortie_facile_backend.core.abstracts.AbstractDomainService;
import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.profile.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends AbstractDomainService<Category> {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        super(categoryRepository, "category");
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category add(Category category) {
        try {
            return categoryRepository.save(category);
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityViolationException("Category name already exists");
        }
    }

    @Override
    public Category updateById(Category category, Long categoryId) {
        Category newCategory = findById(categoryId);
        newCategory.setTitle(category.getTitle());

        return categoryRepository.save(newCategory);
    }

    @Override
    public void deleteById(Long categoryId) {
        Category category = findById(categoryId);

        for (Activity activity : category.getActivityList()) {
            activity.getCategoryList().remove(category);
        }

        for (Profile profile : category.getProfileList()) {
            profile.getCategoryList().remove(category);
        }

        repository.deleteById(categoryId);
    }
}
