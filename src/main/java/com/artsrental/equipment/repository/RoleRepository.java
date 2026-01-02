package com.artsrental.equipment.repository;

import com.artsrental.equipment.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Role entity.
 * 
 * Used for role-based access control (RBAC).
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    /**
     * Find role by name (e.g., "USER", "ADMIN").
     * Used during user registration to assign default role.
     */
    Optional<Role> findByName(String name);
}

