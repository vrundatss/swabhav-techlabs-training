
package com.tss.MiniProject.FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
import com.tss.MiniProject.FoodOrderingSystem.exceptions.DuplicateCategoryException;
import com.tss.MiniProject.FoodOrderingSystem.exceptions.DuplicateItemException;
import com.tss.MiniProject.FoodOrderingSystem.exceptions.ResourceNotFoundException;
import com.tss.MiniProject.FoodOrderingSystem.model.Admin.Admin;
import com.tss.MiniProject.FoodOrderingSystem.model.Constants.Constant;
import com.tss.MiniProject.FoodOrderingSystem.model.Customer.Customer;
import com.tss.MiniProject.FoodOrderingSystem.model.DeliveryAgent.DeliveryAgent;
import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.MenuItem;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.Order;
import com.tss.MiniProject.FoodOrderingSystem.model.enums.DynamicCategoryRegistry;
import com.tss.MiniProject.FoodOrderingSystem.model.enums.ItemCategoryType;
import com.tss.MiniProject.FoodOrderingSystem.util.ValidationUtil;

import java.util.*;
import java.util.stream.Collectors;

public class AdminService {
    private final DataStore db = DataStore.getInstance();
    Scanner sc = new Scanner(System.in);
    Admin admin;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void addAdmin(Admin admin) {
        if (admin == null) {
            throw new IllegalArgumentException("Admin cannot be null!");
        }

        boolean exists = db.getAdmins().stream()
                .anyMatch(a ->
                        a.getUsername().equalsIgnoreCase(admin.getUsername())
                                || (a.getEmail() != null && a.getEmail().equalsIgnoreCase(admin.getEmail()))
                );

        if (exists) {
            throw new IllegalArgumentException("Admin with username or email already exists!");
        }

        db.getAdmins().add(admin);

        System.out.println("Admin " + admin.getName() + " (" + admin.getUsername() + ") successfully added!");
    }

    // --- Agent management ---

    public DeliveryAgent getDeliveryAgentById(String agentId) {
        return db.getAgents().stream()
                .filter(a -> {
                    try {
                        // Handles both numeric & string-based IDs (e.g., "A-12345")
                        return a.getId().equals(String.valueOf(agentId))
                                || a.getId().endsWith(String.valueOf(agentId));
                    } catch (Exception e) {
                        return false;
                    }
                })
                .findFirst()
                .orElse(null);
    }

//    public void addDeliveryAgent(DeliveryAgent agent) {
//        db.getAgents().add(agent);
//    }

    public List<DeliveryAgent> listAllAgents() {
        return db.getAgents();
    }

    public void updateAgentStatus(String agentId, boolean isFree) {
        db.getAgents().stream()
                .filter(a -> a.getId().equals(agentId))
                .findFirst()
                .ifPresent(a -> a.setAvailable(isFree));
    }

    // --- customer management ---
    public List<Customer> listCustomers() {
        return db.getCustomers();
    }

    public void removeCustomer(String id) {
        db.getCustomers().removeIf(c -> c.getId().equals(id));
    }

    // --- CATEGORY MANAGEMENT ---

    public void listCategories() {
        System.out.printf("%-15s | %-10s | %-10s%n", "Category", "Tax Rate", "Discount");
        System.out.println("----------------------------------------------");
        // enum categories
        for (ItemCategoryType cat : ItemCategoryType.values()) {
            System.out.printf("%-15s | %-10.2f | %-10.2f%n",
                    cat.name(), cat.getTaxRate(), cat.getCategoryDiscount());
        }

        // dynamic categories
        for (var dyn : DynamicCategoryRegistry.getAllDynamicCategories()) {
            System.out.printf("%-15s | %-10.2f | %-10.2f%n",
                    dyn.getName(), dyn.getTaxRate(), dyn.getDiscountRate());
        }
    }

    public void addCategory(String categoryName) throws DuplicateCategoryException {
        String cat = categoryName.toUpperCase();

        // Check if category exists (enum or dynamic)
        boolean existsInEnum = Arrays.stream(ItemCategoryType.values())
                .anyMatch(e -> e.name().equalsIgnoreCase(cat));
        boolean existsInDynamic = DynamicCategoryRegistry.exists(cat);

        if (existsInEnum || existsInDynamic) {
            throw new DuplicateCategoryException(
                    "Exception : Category '" + cat + "' already exists in the system!...");
        }

        // Ask user (admin) for rates
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Tax Rate (e.g. 0.05 for 5%): ");
        double tax = sc.nextDouble();

        System.out.print("Enter Discount Rate (e.g. 0.10 for 10%): ");
        double discount = sc.nextDouble();

        DynamicCategoryRegistry.addCategory(cat, tax, discount);

        System.out.println("Category '" + cat + "' successfully added with Tax: " + tax + ", Discount: " + discount);
    }

    // --- ORDER MANAGEMENT ---

    public List<Order> getUserOrderHistory(String customerId) {
        return db.getOrders().stream()
                .filter(o -> o.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }

    // --- REVENUE & PAYMENT MANAGEMENT ---
    public double getTotalPlatformRevenue() {
        return db.getOrders().stream()
                .mapToDouble(o -> o.getFinalAmount())
                .sum();
    }
    public double getTotalAgentPayouts() {
        return db.getOrders().stream()
                .filter(o -> o.getStatus().getName().equalsIgnoreCase("DELIVERED"))
                .mapToDouble(o -> Constant.AGENT_FLAT_PAYOUT)
                .sum();
    }

    public double getCustomerTotalSpent(String customerId) {
        return db.getOrders().stream()
                .filter(o -> o.getCustomerId().equals(customerId))
                .mapToDouble(Order::getFinalAmount)
                .sum();
    }
}