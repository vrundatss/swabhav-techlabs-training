package com.tss.StructuralDesignPattern.AdapterPattern.AdapterStructuralDesignPattern.test;

import com.tss.StructuralDesignPattern.AdapterPattern.AdapterStructuralDesignPattern.model.cart.ShoppingCart;
import com.tss.StructuralDesignPattern.AdapterPattern.AdapterStructuralDesignPattern.model.items.ItemCatalog;
import com.tss.StructuralDesignPattern.AdapterPattern.AdapterStructuralDesignPattern.model.items.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCartAdapterMain {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ShoppingCart cart = new ShoppingCart();
        List<Items> items = new ArrayList<>();

        List<Items> catalog = ItemCatalog.getAvailableItems();

        List<String> itemNames;

        int choice;
        boolean running = true;

        System.out.println("=========================================");
        System.out.println("            SHOPPING CART SYSTEM         ");
        System.out.println("=========================================");

        while (running) {
            System.out.println("\n--------- MENU ---------");
            System.out.println("1. View Catalog");
            System.out.println("2. Add Items to Catalog");
            System.out.println("3. Add Items to Cart");
            System.out.println("4. View Cart Items");
            System.out.println("5. View Currant Cart Price");
            System.out.println("6. Display Cart");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    ItemCatalog.displayCatalog(catalog);
                    break;

                case 2:
                    ItemCatalog.addItemsToCatalog();
                    break;

                case 3:
                    ItemCatalog.displayCatalog(catalog);

                    System.out.print("Enter item ID to add to cart: ");
                    int id = scanner.nextInt();

                    catalog = ItemCatalog.getAvailableItems();
                    if (id > 0 && id <= catalog.size()) {
                        Items selected = catalog.get(id - 1);
                        cart.addItemsToCart(selected);
                        System.out.println(selected.getItemName() + " added to cart!!!...");
                    } else {
                        System.out.println("Invalid item ID...");
                    }
                    break;

                case 4:
                    itemNames = cart.getCartItems();
                    System.out.println("\nItems currently in your cart:");
                    for (String name : itemNames) {
                        System.out.println("-> " + name);
                    }
                    break;

                case 5:
                    System.out.println("Total Price : " + cart.getCartPrice() + " rs.");
                    break;

                case 6:
                    cart.displayCart();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice...Please enter 1–4.");
            }
        }
    }
}
