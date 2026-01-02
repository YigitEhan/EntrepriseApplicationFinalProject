package com.artsrental.equipment.repository;

import com.artsrental.equipment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for User entity.
 * 
 * Used for authentication and user management.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Find user by username.
     * Used by Spring Security for authentication.
     */
    Optional<User> findByUsername(String username);
    
    /**
     * Find user by email.
     * Useful for registration validation (prevent duplicate emails).
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Check if username already exists.
     * Used during registration to ensure unique usernames.
     */
    Boolean existsByUsername(String username);
    
    /**
     * Check if email already exists.
     * Used during registration to ensure unique emails.
     */
    Boolean existsByEmail(String email);
}

