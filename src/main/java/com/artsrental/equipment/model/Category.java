package com.artsrental.equipment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Category entity representing equipment categories.
 * 
 * Design Decision: Category is an ENTITY (not enum) to allow:
 * - Dynamic addition of categories without code changes
 * - Database-driven category management
 * - Easier future expansion (e.g., category descriptions, icons)
 * 
 * Relationship: One Category has Many Products (1:N)
 */
@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, length = 50)
    private String name;
    
    @Column(length = 255)
    private String description;
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();
    
    /**
     * Constructor for easy category creation during seeding.
     */
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

