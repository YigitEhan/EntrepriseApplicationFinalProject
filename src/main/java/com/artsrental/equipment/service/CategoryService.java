package com.artsrental.equipment.service;

import com.artsrental.equipment.model.Category;
import com.artsrental.equipment.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for Category business logic.
 * 
 * Follows the Service pattern to separate business logic from controllers.
 * All database operations go through repositories.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    
    /**
     * Get all categories.
     * Used to populate category filter dropdown.
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    /**
     * Find category by ID.
     */
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
    
    /**
     * Find category by name.
     * Used for filtering products by category name.
     */
    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }
    
    /**
     * Save a new category.
     */
    @Transactional
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
}

