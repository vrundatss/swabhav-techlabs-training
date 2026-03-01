package com.tss.MiniProject.FoodOrderingSystem.controller;

import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
import com.tss.MiniProject.FoodOrderingSystem.exceptions.DuplicateCategoryException;
import com.tss.MiniProject.FoodOrderingSystem.exceptions.DuplicateItemException;
import com.tss.MiniProject.FoodOrderingSystem.exceptions.ResourceNotFoundException;
import com.tss.MiniProject.FoodOrderingSystem.model.Admin.Admin;
import com.tss.MiniProject.FoodOrderingSystem.model.DeliveryAgent.DeliveryAgent;
import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.FoodItem;
import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.MenuItem;
import com.tss.MiniProject.FoodOrderingSystem.model.enums.ItemCategoryType;
import com.tss.MiniProject.FoodOrderingSystem.util.ValidationUtil;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class AdminController {
    private final AppFacade facade;
    private final Scanner sc;

    public AdminController(AppFacade facade, Scanner sc) {
        this.facade = facade;
        this.sc = sc;

    }
    public void handleAdminLogin(Scanner sc ) {
        System.out.print("Enter Username: ");
        String user = ValidationUtil.getValidString(sc, "Username");

        System.out.print("Enter Password: ");
        String pass = ValidationUtil.getValidString(sc, "Password");

        Admin admin = facade.getAuthService().loginAdmin(user, pass);
        if (admin != null) {
            System.out.println("Login Successful! Welcome, " + admin.getUsername());
            facade.getAdminService().setAdmin(admin);
            showMenu();
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    public void showMenu() {
        sc.nextLine();
        while (true) {
            System.out.println("\n========= ADMIN MAIN PANEL =========");
            System.out.println("1. View List of All Delivery Agents");
            System.out.println("2. View List of All Customers");
            System.out.println("3. Category Management");
            System.out.println("4. Menu Management");
            System.out.println("5. Order Management & History");
            System.out.println("6. Reports & Analytics");
            System.out.println("7. Financials (Revenue & Payouts)");
            System.out.println("0. EXIT TO MAIN SWITCH");
            System.out.print("Selection: ");

            int choice = ValidationUtil.getValidInt(sc , "choice" , 0);
            if (choice == 0) break;

            switch (choice) {
                case 1 -> listOfAgents();
                case 2 -> listOfCustomers();
                case 3 -> categorySubMenu();
                case 4 -> menuSubMenu();
                case 5 -> orderSubMenu();
                case 6 -> analyticsSubMenu();
                case 7 -> financialSubMenu();
                default -> System.out.println("Invalid Selection.");
            }
        }
    }

    // --- 1. AGENT ---

    private void listOfAgents(){
        System.out.println("\n--- DELIVERY AGENT LIST ---");
        System.out.println("=".repeat(120));
        System.out.printf("| %-10s | %-15s | %-12s | %-25s | %-12s | %-10s | %-10s | %-10s |%n",
                "ID", "NAME", "PHONE", "EMAIL", "VEHICLE", "STATUS", "EARNINGS", "TOTAL");
        System.out.println("=".repeat(120));

        facade.getAdminService().listAllAgents().forEach(System.out::println);

        System.out.println("=".repeat(120));
    }

    // --- 2. CUSTOMER SUBMENU ---

    private void listOfCustomers(){
        System.out.println("\n--- CUSTOMER LIST ---");
        System.out.println("=".repeat(100));
        System.out.printf("| %-10s | %-15s | %-12s | %-20s | %-20s | %-10s |%n",
                "ID", "NAME", "USERNAME", "EMAIL", "ADDRESS", "PHONE");
        System.out.println("=".repeat(100));

        facade.getAdminService().listCustomers().forEach(System.out::println);

        System.out.println("=".repeat(100));
    }

//    private void customerSubMenu() {
//        while (true) {
//            System.out.println("\n--- CUSTOMER MANAGEMENT ---");
//            System.out.println("1. List All Customers\n2. Remove Customer\n0. BACK");
//            int choice = ValidationUtil.getValidInt(sc , "choice" , 0);
//            if (choice == 0) break;
//            switch (choice) {
//                case 1 -> {
//                    System.out.println("\n--- CUSTOMER LIST ---");
//                    System.out.println("=".repeat(100));
//                    System.out.printf("| %-10s | %-15s | %-12s | %-20s | %-20s | %-10s |%n",
//                            "ID", "NAME", "USERNAME", "EMAIL", "ADDRESS", "PHONE");
//                    System.out.println("=".repeat(100));
//
//                    facade.getAdminService().listCustomers().forEach(System.out::println);
//
//                    System.out.println("=".repeat(100));
//                }
//
//                case 2 -> {
//                    System.out.print("Customer ID to remove: ");
//                    facade.getAdminService().removeCustomer(ValidationUtil.getValidId(sc));
//                }
//            }
//        }
//    }

    // --- 3. CATEGORY SUBMENU ---
    private void categorySubMenu() {
        while (true) {
            System.out.println("\n--- CATEGORY MANAGEMENT ---");
            System.out.println("1. View All Categories");
            System.out.println("2. Add Category");
//            System.out.println("3. Remove All Items from Category");
            System.out.println("0. BACK");
            System.out.print("Choice: ");

            int choice = ValidationUtil.getValidInt(sc , "choice" , 0);
            if (choice == 0) break;

            switch (choice) {
                case 1 -> facade.getAdminService().listCategories();

                case 2 -> handleAddCategory();

            }
        }
    }

    private void handleAddCategory() {
        System.out.print("Enter New Category Name: ");
        String newCat = ValidationUtil.getValidString(sc , "Category").toUpperCase();

        try {
            facade.getAdminService().addCategory(newCat);

            System.out.println("Category " + newCat + " successfully added to system.");
        } catch (DuplicateCategoryException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected exception occurred.");
        }
    }

    // --- 4. MENU SUBMENU ---
    private void menuSubMenu() {
        while (true) {
            System.out.println("\n--- MENU MANAGEMENT ---");
            System.out.println("1. Add Menu Item");
            System.out.println("2. View Current Menu");
            System.out.println("3. Manage Item Availability");
            System.out.println("0. BACK");
            System.out.print("Choice: ");

            int choice = ValidationUtil.getValidInt(sc , "choice" , 0);
            if (choice == 0) break;

            switch (choice) {
                case 1 -> handleAddItem();
                case 2 -> viewCurrentMenuForAdmin();
                case 3 -> manageItemAvailability();
            }
        }
    }

    private void handleAddItem(){
        System.out.println("\n--- Add New Food Item ---");

        System.out.print("Enter Item Name: ");
        String name = ValidationUtil.getValidString(sc, "Item Name");

        if(facade.getMenuService().isItemExists(name)) {
            System.out.println(name + " already exists... Can't add duplicate item.");
            return;
        }

        System.out.print("Enter Base Price: ");
        double price = ValidationUtil.getValidDouble(sc, "Price");

        System.out.println("\nSelect Category:");
        ItemCategoryType[] categories = ItemCategoryType.values();
        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + ". " + categories[i].name());
        }

        System.out.print("Choice (1-" + categories.length + "): ");
        int catChoice = ValidationUtil.getValidInt(sc , "Category choice" , 1);
        ItemCategoryType category = (catChoice > 0 && catChoice <= categories.length)
                ? categories[catChoice - 1]
                : ItemCategoryType.FAST_FOOD;

        String autoId = "ITEM-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();

        MenuItem newItem = new FoodItem.Builder()
                .id(autoId)
                .name(name)
                .price(price)
//                .stock(stock)
                .category(category)
//                .isAvailable(stock > 0)
                .build();

        try {
            facade.getMenuService().addMenuItem(newItem);
            System.out.println("\nItem successfully added with generated ID: " + autoId);
        }catch (DuplicateItemException | DuplicateCategoryException e){
            System.out.println(e.getMessage());
        }
    }

    private void viewCurrentMenuForAdmin(){
        List<MenuItem> menu = facade.getMenuService().listAllMenuItems();

        if (menu.isEmpty()) {
            System.out.println("\nMenu is currently empty. Add items first.");
            return;
        }
        System.out.println("\n" + "=".repeat(85));
        System.out.printf("| %-10s | %-18s | %-15s | %-11s | %-11s |%n",
                "ID", "ITEM NAME", "CATEGORY", "BASE UNIT", "FINAL PRICE");
        System.out.println("-".repeat(85));

        menu.forEach(MenuItem::display);

        System.out.println("=".repeat(85));
    }

    private void manageItemAvailability(){
        System.out.println("\n--- MANAGE ITEM AVAILABILITY ---");
        System.out.print("Enter Item ID: ");
        String id = ValidationUtil.getValidId(sc);

        MenuItem item = facade.getMenuService().getItemById(id);

        if (item != null) {
            boolean currentStatus = item.isAvailable();
            String statusText = currentStatus ? "VISIBLE" : "HIDDEN";
            String actionText = currentStatus ? "HIDE (Disable)" : "SHOW (Enable)";

            System.out.println("Item: " + item.getName());
            System.out.println("Current Status: [" + statusText + "]");
            System.out.print("Are you sure you want to " + actionText + " this item? (yes/no): ");

            if (ValidationUtil.getValidBoolean(sc)) {
                // We pass the opposite of the current status to "flip" it
                facade.getMenuService().toggleItemStatus(id, !currentStatus);
                System.out.println("\u001B[32mSuccess : Item is now " + (!currentStatus ? "VISIBLE" : "HIDDEN") + ".\u001B[0m");
            } else {
                System.out.println("Operation cancelled.");
            }
        } else {
            System.out.println("\u001B[31mException : Item ID not found.\u001B[0m");
        }
    }


    // --- 5. ORDER SUBMENU ---
    private void orderSubMenu() {
        while (true) {
            System.out.println("\n--- ORDER MANAGEMENT ---");
            System.out.println("1. View All Orders\n2. Assign Agent to Pending Order\n3. View Specific User Details\n4. View Notifications\n0. BACK");
            int choice = ValidationUtil.getValidInt(sc , "choice" , 0);
            if (choice == 0) break;
            switch (choice) {
                case 1 -> facade.getOrderService().viewAllOrders();
                case 2 ->{
                    facade.getOrderService().viewAllOrders();

                    System.out.print("Enter Order ID to assign: ");
                    int oid = ValidationUtil.getValidInt(sc , "Order ID" , 1);
                    try {
                        facade.getOrderService().assignOrderToAgent(oid);
                    } catch (ResourceNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.print("Customer ID: ");
                    String customerId= ValidationUtil.getValidId(sc);

                    System.out.println("\n--- ORDER LIST ---");
                    System.out.println("=".repeat(120));
                    System.out.printf("| %-6s | %-12s | %-12s | %-12s | %-10s | %-12s | %-12s | %-20s |%n",
                            "ID", "CUSTOMER", "AGENT", "TOTAL", "DISCOUNT", "FINAL AMT", "STATUS", "CREATED AT");
                    System.out.println("=".repeat(120));

                    facade.getAdminService().getUserOrderHistory(customerId).forEach(System.out::println);

                    System.out.println("=".repeat(120));
                }
                case 4-> notificationMenu();

            }
        }
    }

    private void notificationMenu() {
        System.out.println("\n--- INBOX ---");

        var admin = facade.getAdminService().getAdmin();
        if (admin == null) {
            System.out.println("No admin is currently logged in.");
            return;
        }

        var inbox = admin.getNotifications();

        if (inbox == null || inbox.isEmpty()) {
            System.out.println("No new notifications.");
        } else {
            inbox.forEach(System.out::println);
            System.out.print("\nClear all notifications? (y/n): ");
            if(ValidationUtil.getValidBoolean(sc))
                inbox.clear();
        }
    }

    // --- 6. ANALYTICS SUBMENU ---
    private void analyticsSubMenu() {
        while (true) {
            System.out.println("\n--- REPORTS & ANALYTICS ---");
            System.out.println("1. Top Selling Items\n2. Top Frequent Customers\n0. BACK");
            int choice = ValidationUtil.getValidInt(sc , "choice" , 0);
            if (choice == 0) break;
            switch (choice) {
                case 1 -> facade.getAnalyticsService().getMostFrequentItems(5).forEach(e ->
                        System.out.println(e.getKey() + ": " + e.getValue() + " orders"));
                case 2 -> facade.getAnalyticsService().getMostFrequentCustomers(5).forEach(e ->
                        System.out.println("Customer ID " + e.getKey() + ": " + e.getValue() + " orders"));
            }
        }
    }

    // --- 7. FINANCIAL SUBMENU ---
    private void financialSubMenu() {
        while (true) {
            System.out.println("\n--- REVENUE & PAYMENTS ---");
            System.out.println("1. View Total Platform Revenue\n2. View Total Agent Payouts\n3. Check Customer Total Spending\n0. BACK");
            int choice = ValidationUtil.getValidInt(sc , "choice" , 0);
            if (choice == 0) break;
            switch (choice) {
                case 1 -> System.out.println("Total Platform Revenue: " + facade.getAdminService().getTotalPlatformRevenue());
                case 2 -> System.out.println("Total Agent Payouts: " + facade.getAdminService().getTotalAgentPayouts());
                case 3 -> {
                    System.out.print("Customer ID: ");
                    System.out.println("Total Spent: " + facade.getAdminService().getCustomerTotalSpent(sc.next()));
                }
            }
        }
    }
}