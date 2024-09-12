package com.poec.sortie_facile_backend.domain.category;

import com.poec.sortie_facile_backend.core.abstracts.AbstractDomainService;
import com.poec.sortie_facile_backend.domain.activity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Category updateById(Category category, Long categoryId) {
        Category newCategory = findById(categoryId);
        newCategory.setTitle(category.getTitle());

        return categoryRepository.save(newCategory);
    }

    @Override
    public void deleteById(Long categoryId) {
        Category category = findById(categoryId);

        for (Activity activity : category.getActivities()) {
            activity.setCategory(null);
        }

        repository.deleteById(categoryId);
    }
}
