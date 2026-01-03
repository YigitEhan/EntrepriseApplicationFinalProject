package com.artsrental.equipment.controller;

import com.artsrental.equipment.model.Product;
import com.artsrental.equipment.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

/**
 * Shopping cart controller.
 * 
 * Handles:
 * - Adding products to cart
 * - Viewing cart
 * - Removing products from cart
 * - Updating quantities
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    /**
     * View shopping cart.
     * 
     * @param session the HTTP session
     * @param model the model
     * @return cart view
     */
    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        Map<Product, Integer> cartItems = cartService.getCartItems(session);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cartTotal", cartService.getCartTotal(session));
        model.addAttribute("cartItemCount", cartService.getCartItemCount(session));
        return "cart";
    }
    
    /**
     * Add product to cart.
     * 
     * @param productId the product ID
     * @param quantity the quantity (default 1)
     * @param session the HTTP session
     * @param redirectAttributes redirect attributes for flash messages
     * @return redirect to catalog
     */
    @PostMapping("/add")
    public String addToCart(
            @RequestParam Long productId,
            @RequestParam(defaultValue = "1") int quantity,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        try {
            cartService.addToCart(session, productId, quantity);
            redirectAttributes.addFlashAttribute("success", "Product added to cart!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to add product to cart");
        }
        
        return "redirect:/catalog";
    }
    
    /**
     * Remove product from cart.
     * 
     * @param productId the product ID
     * @param session the HTTP session
     * @param redirectAttributes redirect attributes for flash messages
     * @return redirect to cart
     */
    @PostMapping("/remove")
    public String removeFromCart(
            @RequestParam Long productId,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        try {
            cartService.removeFromCart(session, productId);
            redirectAttributes.addFlashAttribute("success", "Product removed from cart");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to remove product");
        }
        
        return "redirect:/cart";
    }
    
    /**
     * Update product quantity in cart.
     * 
     * @param productId the product ID
     * @param quantity the new quantity
     * @param session the HTTP session
     * @param redirectAttributes redirect attributes for flash messages
     * @return redirect to cart
     */
    @PostMapping("/update")
    public String updateQuantity(
            @RequestParam Long productId,
            @RequestParam int quantity,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        try {
            cartService.updateQuantity(session, productId, quantity);
            redirectAttributes.addFlashAttribute("success", "Cart updated");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update cart");
        }
        
        return "redirect:/cart";
    }
    
    /**
     * Clear the cart.
     * 
     * @param session the HTTP session
     * @param redirectAttributes redirect attributes for flash messages
     * @return redirect to cart
     */
    @PostMapping("/clear")
    public String clearCart(HttpSession session, RedirectAttributes redirectAttributes) {
        cartService.clearCart(session);
        redirectAttributes.addFlashAttribute("success", "Cart cleared");
        return "redirect:/cart";
    }
}

