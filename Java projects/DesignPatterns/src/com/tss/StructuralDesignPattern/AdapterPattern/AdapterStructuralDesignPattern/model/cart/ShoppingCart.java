package com.tss.StructuralDesignPattern.AdapterPattern.AdapterStructuralDesignPattern.model.cart;

import com.tss.StructuralDesignPattern.AdapterPattern.AdapterStructuralDesignPattern.model.items.Items;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    List<Items> items = new ArrayList<>();

    public ShoppingCart() {
        this.items = items;
    }

    public List<Items> getItems() {
        return items;
    }

    public void addItemsToCart(Items item) {
        items.add(item);
    }

    public List<String> getCartItems() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty...Add items first...");
            return null;
        }
        List<String> itemNames = new ArrayList<>();
        for (Items item : items) {
            itemNames.add(item.getItemName());
        }
        return itemNames;
    }

    public Double getCartPrice() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty...Add items first...");
            return 0.0;
        }

        Double total = 0.0;
        for (Items item : items) {
            total += item.getItemPrice();
        }
        return total;
    }

    public void displayCart() {
        if (this.items.isEmpty()) {
            System.out.println("Cart is empty...Add items first...");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("            ️   SHOPPING CART            ");
        System.out.println("========================================");
        System.out.printf("%-25s %10s%n", "Item Name", "Price (rs.)");
        System.out.println("----------------------------------------");

        for (Items item : this.items) {
            System.out.printf("%-25s %10.2f%n", item.getItemName(), item.getItemPrice());
        }

        System.out.println("----------------------------------------");
        System.out.printf("%-25s %10.2f%n", "TOTAL : ", getCartPrice());
        System.out.println("========================================");

    }


}
