package com.artsrental.equipment.controller;

import com.artsrental.equipment.model.Product;
import com.artsrental.equipment.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Checkout controller.
 * 
 * Handles:
 * - Checkout page (cart summary)
 * - Confirmation of reservation
 * - Clearing cart after confirmation
 */
@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    
    @Autowired
    private CartService cartService;
    
    /**
     * Show checkout page with cart summary.
     * 
     * @param session the HTTP session
     * @param authentication the authenticated user
     * @param model the model
     * @param redirectAttributes redirect attributes for flash messages
     * @return checkout view or redirect to cart if empty
     */
    @GetMapping
    public String showCheckout(
            HttpSession session,
            Authentication authentication,
            Model model,
            RedirectAttributes redirectAttributes) {
        
        // Check if cart is empty
        if (cartService.isCartEmpty(session)) {
            redirectAttributes.addFlashAttribute("error", "Your cart is empty");
            return "redirect:/cart";
        }
        
        Map<Product, Integer> cartItems = cartService.getCartItems(session);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cartTotal", cartService.getCartTotal(session));
        model.addAttribute("username", authentication.getName());
        
        return "checkout";
    }
    
    /**
     * Confirm reservation and clear cart.
     * 
     * @param session the HTTP session
     * @param authentication the authenticated user
     * @param model the model
     * @param redirectAttributes redirect attributes for flash messages
     * @return confirmation view or redirect to cart if empty
     */
    @PostMapping("/confirm")
    public String confirmCheckout(
            HttpSession session,
            Authentication authentication,
            Model model,
            RedirectAttributes redirectAttributes) {
        
        // Check if cart is empty
        if (cartService.isCartEmpty(session)) {
            redirectAttributes.addFlashAttribute("error", "Your cart is empty");
            return "redirect:/cart";
        }
        
        // Get cart details before clearing
        Map<Product, Integer> cartItems = cartService.getCartItems(session);
        int itemCount = cartService.getCartItemCount(session);
        
        // Generate confirmation number
        String confirmationNumber = generateConfirmationNumber();
        
        // Clear the cart
        cartService.clearCart(session);
        
        // Add confirmation details to model
        model.addAttribute("confirmationNumber", confirmationNumber);
        model.addAttribute("username", authentication.getName());
        model.addAttribute("itemCount", itemCount);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        
        return "confirmation";
    }
    
    /**
     * Generate a unique confirmation number.
     * 
     * @return confirmation number
     */
    private String generateConfirmationNumber() {
        return "RES-" + System.currentTimeMillis();
    }
}

