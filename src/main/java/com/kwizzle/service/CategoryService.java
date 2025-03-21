package com.kwizzle.service;

import com.kwizzle.model.Category;
import com.kwizzle.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category saveCategory(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new IllegalArgumentException("Nama kategori sudah digunakan");
        }

        category.setCreatedAt(LocalDateTime.now());
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Optional<Category> updateCategory(Long id, Category updatedCategory) {
        return categoryRepository.findById(id).map(existing -> {
            if (!existing.getName().equals(updatedCategory.getName()) &&
                    categoryRepository.existsByName(updatedCategory.getName())) {
                throw new IllegalArgumentException("Nama kategori sudah digunakan");
            }

            existing.setName(updatedCategory.getName());
            existing.setDescription(updatedCategory.getDescription());
            return categoryRepository.save(existing);
        });
    }

    public boolean deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}