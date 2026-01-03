package com.artsrental.equipment.service;

import com.artsrental.equipment.model.Product;
import com.artsrental.equipment.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Session-based shopping cart service.
 * 
 * Cart is stored in HttpSession as Map<Long, Integer> (productId -> quantity).
 * Provides add, remove, update, and view operations.
 */
@Service
public class CartService {
    
    private static final String CART_SESSION_KEY = "shopping_cart";
    
    @Autowired
    private ProductRepository productRepository;
    
    /**
     * Get the cart from session.
     * Creates a new cart if it doesn't exist.
     * 
     * @param session the HTTP session
     * @return the cart map (productId -> quantity)
     */
    @SuppressWarnings("unchecked")
    public Map<Long, Integer> getCart(HttpSession session) {
        Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }
    
    /**
     * Add a product to the cart.
     * If product already exists, increment quantity.
     * 
     * @param session the HTTP session
     * @param productId the product ID
     * @param quantity the quantity to add
     */
    public void addToCart(HttpSession session, Long productId, int quantity) {
        Map<Long, Integer> cart = getCart(session);
        cart.merge(productId, quantity, Integer::sum);
    }
    
    /**
     * Remove a product from the cart.
     * 
     * @param session the HTTP session
     * @param productId the product ID
     */
    public void removeFromCart(HttpSession session, Long productId) {
        Map<Long, Integer> cart = getCart(session);
        cart.remove(productId);
    }
    
    /**
     * Update product quantity in cart.
     * 
     * @param session the HTTP session
     * @param productId the product ID
     * @param quantity the new quantity
     */
    public void updateQuantity(HttpSession session, Long productId, int quantity) {
        if (quantity <= 0) {
            removeFromCart(session, productId);
        } else {
            Map<Long, Integer> cart = getCart(session);
            cart.put(productId, quantity);
        }
    }
    
    /**
     * Get cart items with product details.
     * 
     * @param session the HTTP session
     * @return map of Product -> quantity
     */
    public Map<Product, Integer> getCartItems(HttpSession session) {
        Map<Long, Integer> cart = getCart(session);
        Map<Product, Integer> cartItems = new HashMap<>();
        
        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
            productRepository.findById(entry.getKey()).ifPresent(product -> 
                cartItems.put(product, entry.getValue())
            );
        }
        
        return cartItems;
    }
    
    /**
     * Calculate total price of cart.
     * 
     * @param session the HTTP session
     * @return total price
     */
    public BigDecimal getCartTotal(HttpSession session) {
        Map<Product, Integer> cartItems = getCartItems(session);
        return cartItems.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    /**
     * Get number of items in cart.
     * 
     * @param session the HTTP session
     * @return total number of items
     */
    public int getCartItemCount(HttpSession session) {
        Map<Long, Integer> cart = getCart(session);
        return cart.values().stream().mapToInt(Integer::intValue).sum();
    }
    
    /**
     * Clear the cart.
     * 
     * @param session the HTTP session
     */
    public void clearCart(HttpSession session) {
        session.removeAttribute(CART_SESSION_KEY);
    }
    
    /**
     * Check if cart is empty.
     * 
     * @param session the HTTP session
     * @return true if cart is empty
     */
    public boolean isCartEmpty(HttpSession session) {
        Map<Long, Integer> cart = getCart(session);
        return cart.isEmpty();
    }
}

