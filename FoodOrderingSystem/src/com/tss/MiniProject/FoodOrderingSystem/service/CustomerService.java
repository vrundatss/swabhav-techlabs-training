package com.tss.MiniProject.FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
import com.tss.MiniProject.FoodOrderingSystem.model.Customer.Customer;
import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.MenuItem;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.Order;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.PendingStatus;
import com.tss.MiniProject.FoodOrderingSystem.model.enums.ItemCategoryType;
import com.tss.MiniProject.FoodOrderingSystem.util.ValidationUtil;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Scanner;
import static com.tss.MiniProject.FoodOrderingSystem.util.ColorUtils.*;

public class CustomerService {
    private final DataStore db = DataStore.getInstance();

    Scanner sc = new Scanner(System.in);

    public CustomerService() {}


    public void registerCustomer(Customer customer) {
        db.getCustomers().add(customer);
    }

    public Customer getCustomerById(String customerId) {
        return db.getCustomers().stream()
                .filter(c -> c.getId().equalsIgnoreCase(customerId))
                .findFirst()
                .orElse(null);
    }

    public void viewByCategory() {
        System.out.println("\n" + "\u001B[35m" + "--- SELECT CATEGORY ---" + "\u001B[0m");
        ItemCategoryType[] categories = ItemCategoryType.values();

        for (int i = 0; i < categories.length; i++) {
            System.out.printf("%d. %-15s %n", (i + 1), categories[i].name());
        }
        System.out.println("0. BACK");
        System.out.print("Select Category Number: ");

        int catChoice = ValidationUtil.getValidInt(sc, "Category Number", 0);

        if (catChoice > 0 && catChoice <= categories.length) {
            ItemCategoryType selectedEnum = categories[catChoice - 1];
            String catName = selectedEnum.name();

            // fetch items  by enum category
            var items = db.getMenuItems().stream()
                    .filter(MenuItem::isAvailable)
                    .filter(i -> i.getCategory() != null)
                    .filter(i -> i.getCategory().name().equalsIgnoreCase(catName))
                    .toList();

            if (items.isEmpty()) {
                System.out.println(YELLOW + "No items currently available in " + catName + "." + RESET);
            } else {
                displayHeader(catName);
                items.forEach(item -> {
                    double finalPrice = selectedEnum.calculateFinalPrice(item.getPrice());
                    System.out.printf("| %-10s | %-25s | ₹%-9.2f |%n",
                            item.getId(), item.getName(), item.getPrice(), finalPrice);
                });
                System.out.println("=".repeat(80));
            }
        }
    }

    private void displayHeader(String catName) {
        System.out.println("\n" + "\u001B[1;37m" + "Items in " + catName.toUpperCase() + "\u001B[0m");
        System.out.println("=".repeat(80));
        System.out.printf("| %-10s | %-25s | %-10s |%n",
                "ID", "NAME", "BASE");
        System.out.println("=".repeat(80));
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