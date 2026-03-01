package com.tss.MiniProject.FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
import com.tss.MiniProject.FoodOrderingSystem.exceptions.ResourceNotFoundException;
import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.MenuItem;

import java.util.HashMap;
import java.util.Map;

public class CartService {

    private final DataStore db =DataStore.getInstance();

    // Map of CustomerID ==> (MenuItemID , Quantity)
    private final Map<String, Map<String, Integer>> activeCarts = new HashMap<>();

    public void addToCart(String customerId, String menuItemId, int quantity) {
        activeCarts.computeIfAbsent(customerId, k -> new HashMap<>())
                .merge(menuItemId, quantity, Integer::sum);
    }

    public Map<String, Integer> getCart(String customerId) {
        return activeCarts.getOrDefault(customerId, new HashMap<>());
    }

    public void removeFromCart(String customerId, String itemId) {
        Map<String, Integer> cart = getCart(customerId);
        if (cart.containsKey(itemId)) {
            cart.remove(itemId);
            System.out.println("Item removed from cart.");
        } else {
            try {
                throw new ResourceNotFoundException("Item not found in cart.");
            } catch (ResourceNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void clearCart(String customerId) {
        activeCarts.remove(customerId);
    }

}