package com.tss.MiniProject.FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
import com.tss.MiniProject.FoodOrderingSystem.model.Customer.Customer;
import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.MenuItem;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.Order;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.PendingStatus;
import com.tss.MiniProject.FoodOrderingSystem.model.enums.ItemCategoryType;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.tss.MiniProject.FoodOrderingSystem.util.ColorUtils.*;

public class CustomerService {
    private final DataStore db = DataStore.getInstance();

    public CustomerService() {}


    public void registerCustomer(Customer customer) {
        db.getCustomers().add(customer);
    }

    public Optional<Customer> login(String loginInput, String plainPassword) {
//        String hashedInput = PasswordUtil.hash(plainPassword); // Hash the input first!

        return db.getCustomers().stream()
                .filter(c -> (loginInput.equals(c.getUsername()) || loginInput.equals(c.getEmail()))
                        && plainPassword.equals(c.getPassword()))
                .findFirst();
    }

    public Customer getCustomerById(String customerId) {
        return db.getCustomers().stream()
                .filter(c -> c.getId().equalsIgnoreCase(customerId))
                .findFirst()
                .orElse(null);
    }

    public List<MenuItem> viewByCategory(String categoryName) {
        return db.getMenuItems().stream()
                // FIRST: Only take items that are NOT hidden
                .filter(MenuItem::isAvailable)
                // SECOND: Filter by the chosen category
                .filter(item -> item.getCategory().name().equalsIgnoreCase(categoryName))
                .collect(Collectors.toList());
    }


    public void viewMenuCategorized() {
        // 1. Grouping ONLY active items by Category
        Map<ItemCategoryType, List<MenuItem>> groupedMenu = db.getMenuItems().stream()
                // FILTER: Only include items where active == true
                .filter(MenuItem::isAvailable)
                .collect(Collectors.groupingBy(MenuItem::getCategory));

        if (groupedMenu.isEmpty()) {
            System.out.println(YELLOW + "The menu is currently empty or all items are unavailable." + RESET);
            return;
        }

        System.out.println("\n" + "═".repeat(75));
        System.out.printf(WHITE_BOLD + " %-50s %n" + RESET, "FOOD MENU");
        System.out.println("═".repeat(75));

        // 2. Iterate through each category group
        groupedMenu.forEach((category, items) -> {
            // Print Category Header
            System.out.println("\n" + PURPLE + category.name() + RESET);
            System.out.println("─".repeat(85)); // Increased width for the extra column

            // Header with the Final Price column
            System.out.printf(CYAN + "| %-12s | %-35s | %-12s | %-15s |%n" + RESET,
                    "ID", "NAME", "PRICE", "FINAL (UNIT)");

            // 3. Print the available items
            for (MenuItem item : items) {
                // Calculate the Final Price using the category key from the map
                double finalPrice = category.calculateFinalPrice(item.getPrice());

                System.out.printf("| %-12s | %-35s | ₹%-11.2f | \u001B[32m₹%-14.2f\u001B[0m |%n",
                        item.getId(),
                        item.getName(),
                        item.getPrice(),
                        finalPrice);
            }
        });
        System.out.println("═".repeat(85));
    }

    // ===> ORDERING FLOW

    public Order checkout(String customerId, Map<String, Integer> cart, double total, double discount, double finalAmount, String discountStrategyName) {
        if (cart == null || cart.isEmpty())
            throw new IllegalStateException("Cart is empty!");

        Order order = new Order();
        order.setId(db.getOrders().size() + 1);
        order.setCustomerId(customerId);
        order.setItemIdQty(new HashMap<>(cart)); // copy cart items to the order
        order.setTotalAmount(total);
        order.setDiscount(discount);
        order.setFinalAmount(finalAmount);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(new PendingStatus());
        order.setAppliedDiscountStrategy(discountStrategyName);

        db.getOrders().add(order);
        return order;
    }

}