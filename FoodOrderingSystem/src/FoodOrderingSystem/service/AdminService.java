
package FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
import com.tss.MiniProject.FoodOrderingSystem.exceptions.DuplicateCategoryException;
import com.tss.MiniProject.FoodOrderingSystem.exceptions.ResourceNotFoundException;
import com.tss.MiniProject.FoodOrderingSystem.model.Admin.Admin;
import com.tss.MiniProject.FoodOrderingSystem.model.Customer.Customer;
import com.tss.MiniProject.FoodOrderingSystem.model.DeliveryAgent.DeliveryAgent;
import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.MenuItem;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.Order;
import com.tss.MiniProject.FoodOrderingSystem.model.enums.DynamicCategoryRegistry;
import com.tss.MiniProject.FoodOrderingSystem.model.enums.ItemCategoryType;

import java.util.*;
import java.util.stream.Collectors;

public class AdminService {
    private final DataStore db = DataStore.getInstance();
    Scanner sc = new Scanner(System.in);
    Admin admin;
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

    public void addDeliveryAgent(DeliveryAgent agent) {
        db.getAgents().add(agent);
    }

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

    // --- MENU MANAGEMENT ---
    public void addMenuItem(MenuItem newItem) {
        // 1. Check if item with same name already exists (case-insensitive)
        Optional<MenuItem> existingItem = db.getMenuItems().stream()
                .filter(item -> item.getName().equalsIgnoreCase(newItem.getName()))
                .findFirst();

        if (existingItem.isPresent()) {
            MenuItem item = existingItem.get();

            if (item.getCategory() != newItem.getCategory()) {
                throw new DuplicateCategoryException("Conflict: '" + newItem.getName() +
                        "' already exists in " + item.getCategory() + ". Cannot add to " + newItem.getCategory());
            }
            item.addStock(newItem.getStock());
        } else {
            db.getMenuItems().add(newItem);
        }
    }

    public MenuItem getItemById(String itemId) {
        for (MenuItem item : db.getMenuItems()) {
            if (item.getId().equalsIgnoreCase(itemId)) {
                return item;
            }
        }
        return null;
    }

    public List<MenuItem> listAllMenuItems() {
        return db.getMenuItems(); // Simply returns the list from DataStore
    }

    // --- CATEGORY MANAGEMENT LOGIC ---

//    public void listCategories() {
//        System.out.println(String.format("%-15s | %-10s | %-10s", "Category", "Tax Rate", "Discount"));
//        System.out.println("----------------------------------------------");
//        for (ItemCategoryType cat : ItemCategoryType.values()) {
//            System.out.println(String.format("%-15s | %-10.2f | %-10.2f",
//                    cat.name(), cat.getTaxRate(), cat.getCategoryDiscount()));
//        }
//    }

    public void listCategories() {
        System.out.printf("%-15s | %-10s | %-10s%n", "Category", "Tax Rate", "Discount");
        System.out.println("----------------------------------------------");

        // Static (enum) categories
        for (ItemCategoryType cat : ItemCategoryType.values()) {
            System.out.printf("%-15s | %-10.2f | %-10.2f%n",
                    cat.name(), cat.getTaxRate(), cat.getCategoryDiscount());
        }

        // Dynamic categories
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

    public boolean removeItemById(String itemId) {
        // this returns true if an element was actually removed
        return db.getMenuItems().removeIf(item -> item.getId().equalsIgnoreCase(itemId));
    }

    public void removeCategoryItems(ItemCategoryType category) {
        long count = db.getMenuItems().stream()
                .filter(item -> item.getCategory() == category)
                .count();

        if (count == 0) {
            try {
                throw new ResourceNotFoundException("Exception : No items found in category " + category.name() + " to remove.");
            } catch (ResourceNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        db.getMenuItems().removeIf(item -> item.getCategory() == category);
        System.out.println("Successfully Removed " + count + " items from " + category.name());
    }

//    public List<MenuItem> getItemsByCategory(ItemCategoryType category) {
//        return db.getMenuItems().stream()
//                .filter(item -> item.getCategory() == category)
//                .toList();
//    }
//
//    public void clearCategory(ItemCategoryType category) {
//        int initialSize = db.getMenuItems().size();
//        db.getMenuItems().removeIf(item -> item.getCategory() == category);
//        int removedCount = initialSize - db.getMenuItems().size();
//        System.out.println("Removed " + removedCount + " items from " + category.name());
//    }

    // --- ORDER MANAGEMENT ---
    public List<Order> getAllOrders() {
        return db.getOrders();
    }

    public List<Order> getUserOrderHistory(String customerId) {
        return db.getOrders().stream()
                .filter(o -> o.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }

    private void notificationMenu() {
        System.out.println("\n--- INBOX ---");
        // Assuming currentAccount (Customer/Admin/Agent) is logged in
        var inbox = admin.getNotifications();

        if (inbox.isEmpty()) {
            System.out.println("No new notifications.");
        } else {
            inbox.forEach(System.out::println);
            System.out.print("\nClear all notifications? (y/n): ");
            if (sc.next().equalsIgnoreCase("y")) inbox.clear();
        }
    }

    // --- REPORTS & ANALYTICS ---
    public List<Map.Entry<String, Long>> getMostFrequentItems(int topN) {
        return db.getOrders().stream()
                .flatMap(order -> order.getItemIdQty().entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingLong(Map.Entry::getValue)))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(topN)
                .collect(Collectors.toList());
    }

    public List<Map.Entry<String, Long>> getMostFrequentCustomers(int topN) {
        return db.getOrders().stream()
                .collect(Collectors.groupingBy(Order::getCustomerId, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(topN)
                .collect(Collectors.toList());
    }

    // --- REVENUE & PAYMENT MANAGEMENT ---
    public double getTotalPlatformRevenue() {
        return db.getOrders().stream()
                .mapToDouble(o -> o.getFinalAmount() * 0.50)
                .sum();
    }

    public double getTotalAgentPayouts() {
        return db.getOrders().stream()
                .mapToDouble(o -> o.getFinalAmount() * 0.50)
                .sum();
    }

    public double getCustomerTotalSpent(String customerId) {
        return db.getOrders().stream()
                .filter(o -> o.getCustomerId().equals(customerId))
                .mapToDouble(Order::getFinalAmount)
                .sum();
    }
}



//package com.tss.MiniProject.FoodOrderingSystem.service;
//
//import com.tss.MiniProject.FoodOrderingSystem.model.Order.Order;
//import com.tss.MiniProject.FoodOrderingSystem.model.DeliveryAgent.DeliveryAgent;
//import com.tss.MiniProject.FoodOrderingSystem.model.Customer.Customer;
//import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.MenuItem;
//import com.tss.MiniProject.FoodOrderingSystem.repository.*;
//
//import java.util.*;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//public class AdminService {
//    private final CustomerRepository customerRepo;
//    private final MenuItemRepository menuRepo;
//    private final OrderRepository orderRepo;
//    private final DeliveryAgentRepository agentRepo;
//
//    public AdminService(CustomerRepository customerRepo,
//                        MenuItemRepository menuRepo,
//                        OrderRepository orderRepo,
//                        DeliveryAgentRepository agentRepo) {
//        this.customerRepo = customerRepo;
//        this.menuRepo = menuRepo;
//        this.orderRepo = orderRepo;
//        this.agentRepo = agentRepo;
//    }
//
//    // CRUD for delivery agents
//    public void addDeliveryAgent(DeliveryAgent a) { agentRepo.add(a); }
//    public void updateDeliveryAgent(DeliveryAgent a) { agentRepo.update(a); }
//    public void removeDeliveryAgent(String id) { var list = agentRepo.getAll().stream().filter(x -> !x.getId().equals(id)).toList(); agentRepo.saveAll(list); }
//
//    // Customers
//    public List<Customer> listCustomers() { return customerRepo.getAll(); }
//    public void removeCustomer(String id) { var list = customerRepo.getAll().stream().filter(c -> !c.getId().equals(id)).toList(); customerRepo.saveAll(list); }
//
//    // Menu
//    public void addMenuItem(MenuItem item) { menuRepo.add(item); }
//    public List<MenuItem> listMenu() { return menuRepo.getAll(); }
//    public void updateMenu(List<MenuItem> newList) { menuRepo.saveAll(newList); }
//
//    // Orders
//    public List<Order> allOrders() { return orderRepo.getAll(); }
//
//    // Analytics: most frequent ordered items
//    public List<Map.Entry<String, Long>> mostFrequentItems(int topN) {
//        Map<String, Long> counts = new HashMap<>();
//        for (Order o : orderRepo.getAll()) {
//            o.getItemIdQty().forEach((id, qty) -> counts.put(id, counts.getOrDefault(id, 0L) + qty));
//        }
//        return counts.entrySet().stream()
//                .sorted(Map.Entry.<String,Long>comparingByValue().reversed())
//                .limit(topN)
//                .toList();
//    }
//
//    // Most frequent customer
//    public List<Map.Entry<Integer, Long>> mostFrequentCustomers(int topN) {
//        Map<Integer, Long> counts = orderRepo.getAll().stream()
//                .collect(Collectors.groupingBy(Order::getCustomerId, Collectors.counting()));
//        return counts.entrySet().stream().sorted(Map.Entry.<Integer,Long>comparingByValue().reversed()).limit(topN).toList();
//    }
//
//    // Revenue (admin commission, 50% of finalAmount)
//    public double totalPlatformRevenue() {
//        return orderRepo.getAll().stream()
//                .mapToDouble(o -> o.getFinalAmount() * 0.50) // admin 50%
//                .sum();
//    }
//
//    // Total payout to delivery agents
//    public double totalAgentPayout() {
//        return orderRepo.getAll().stream()
//                .mapToDouble(o -> o.getFinalAmount() * 0.50) // agent 50%
//                .sum();
//    }
//
//    // View user order history
//    public List<Order> getUserOrders(int customerId) {
//        return orderRepo.findByCustomer(customerId);
//    }
//}