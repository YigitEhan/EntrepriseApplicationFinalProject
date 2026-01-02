package com.artsrental.equipment.controller;

import com.artsrental.equipment.model.Category;
import com.artsrental.equipment.model.Product;
import com.artsrental.equipment.service.CategoryService;
import com.artsrental.equipment.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controller for the equipment catalog.
 * 
 * Handles:
 * - Displaying all products
 * - Filtering products by category
 * - Providing category list for filter dropdown
 * 
 * MVC Pattern: Controller receives requests, calls Service layer,
 * and returns view name with model data.
 */
@Controller
@RequiredArgsConstructor
public class CatalogController {
    
    private final ProductService productService;
    private final CategoryService categoryService;
    
    /**
     * Display catalog page with optional category filtering.
     * 
     * URL: /catalog or /catalog?category=Lighting
     * 
     * @param categoryId Optional category ID for filtering
     * @param model Spring MVC model to pass data to view
     * @return View name (catalog.html)
     */
    @GetMapping({"/", "/catalog"})
    public String showCatalog(
            @RequestParam(required = false) Long categoryId,
            Model model) {
        
        List<Product> products;
        Category selectedCategory = null;
        
        // Filter by category if categoryId is provided
        if (categoryId != null) {
            selectedCategory = categoryService.getCategoryById(categoryId).orElse(null);
            if (selectedCategory != null) {
                products = productService.getProductsByCategory(selectedCategory);
            } else {
                products = productService.getAllProducts();
            }
        } else {
            // Show all products if no filter
            products = productService.getAllProducts();
        }
        
        // Get all categories for the filter dropdown
        List<Category> categories = categoryService.getAllCategories();
        
        // Add data to model for Thymeleaf template
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategory", selectedCategory);
        
        return "catalog";
    }
}

