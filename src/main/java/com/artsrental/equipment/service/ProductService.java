package com.artsrental.equipment.service;

import com.artsrental.equipment.model.Category;
import com.artsrental.equipment.model.Product;
import com.artsrental.equipment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for Product business logic.
 * 
 * Handles product retrieval, filtering, and management.
 * Implements the business rules for the equipment catalog.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    
    private final ProductRepository productRepository;
    
    /**
     * Get all products.
     * Used to display the full catalog.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    /**
     * Get all available products.
     * Shows only equipment that can be rented.
     */
    public List<Product> getAvailableProducts() {
        return productRepository.findByAvailableTrue();
    }
    
    /**
     * Get products by category.
     * Used for category-based filtering.
     */
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }
    
    /**
     * Get available products by category.
     * Combines category filtering with availability check.
     */
    public List<Product> getAvailableProductsByCategory(Category category) {
        return productRepository.findByCategoryAndAvailableTrue(category);
    }
    
    /**
     * Find product by ID.
     * Used for product details and cart operations.
     */
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    
    /**
     * Save a new product.
     */
    @Transactional
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}

