package com.artsrental.equipment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Role entity for user authorization.
 *
 * Supports role-based access control (RBAC).
 * Common roles: USER, ADMIN
 *
 * Relationship: Many-to-Many with User
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    /**
     * Constructor for easy role creation during seeding.
     */
    public Role(String name) {
        this.name = name;
    }
}

