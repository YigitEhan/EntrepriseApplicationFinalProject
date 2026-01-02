package com.artsrental.equipment.repository;

import com.artsrental.equipment.model.Category;
import com.artsrental.equipment.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Product entity.
 * 
 * Provides CRUD operations and custom queries for product filtering.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    /**
     * Find all products by category.
     * Used for category-based filtering in the catalog.
     */
    List<Product> findByCategory(Category category);
    
    /**
     * Find all available products.
     * Useful for showing only rentable equipment.
     */
    List<Product> findByAvailableTrue();
    
    /**
     * Find available products by category.
     * Combines filtering by category and availability.
     */
    List<Product> findByCategoryAndAvailableTrue(Category category);
}

