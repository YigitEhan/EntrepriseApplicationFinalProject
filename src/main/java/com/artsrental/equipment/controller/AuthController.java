package com.artsrental.equipment.controller;

import com.artsrental.equipment.model.User;
import com.artsrental.equipment.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Authentication controller for login and registration.
 * 
 * Handles:
 * - User registration with validation
 * - Login page display (authentication handled by Spring Security)
 */
@Controller
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    /**
     * Show login page.
     * 
     * @param error optional error parameter
     * @param logout optional logout parameter
     * @param model the model
     * @return login view
     */
    @GetMapping("/login")
    public String showLoginPage(
            @org.springframework.web.bind.annotation.RequestParam(required = false) String error,
            @org.springframework.web.bind.annotation.RequestParam(required = false) String logout,
            Model model) {
        
        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
        }
        
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully");
        }
        
        return "login";
    }
    
    /**
     * Show registration page.
     * 
     * @param model the model
     * @return registration view
     */
    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    
    /**
     * Process registration form.
     * 
     * @param user the user to register
     * @param bindingResult validation results
     * @param model the model
     * @return redirect to login on success, or registration view on error
     */
    @PostMapping("/register")
    public String registerUser(
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult,
            Model model) {
        
        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return "register";
        }
        
        // Check if username already exists
        if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
        
        // Check if email already exists
        if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Email already exists");
            return "register";
        }
        
        try {
            // Register the user
            userService.registerUser(user);
            model.addAttribute("success", "Registration successful! Please login.");
            return "redirect:/login?registered";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "register";
        }
    }
    
    /**
     * Home page redirect.
     * 
     * @return redirect to catalog
     */
    @GetMapping("/")
    public String home() {
        return "redirect:/catalog";
    }
}

