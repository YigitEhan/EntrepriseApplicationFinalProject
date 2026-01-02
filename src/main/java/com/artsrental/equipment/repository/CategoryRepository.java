package com.artsrental.equipment.repository;

import com.artsrental.equipment.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Category entity.
 * 
 * Spring Data JPA automatically provides CRUD operations.
 * Custom query methods follow Spring Data naming conventions.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    /**
     * Find category by name.
     * Useful for filtering products by category name.
     */
    Optional<Category> findByName(String name);
}

