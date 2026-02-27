package FoodOrderingSystem.controller;

import com.tss.MiniProject.FoodOrderingSystem.exceptions.DuplicateCategoryException;
import com.tss.MiniProject.FoodOrderingSystem.exceptions.ResourceNotFoundException;
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

    public void showMenu() {
        sc.nextLine();
        while (true) {
            System.out.println("\n========= ADMIN MAIN PANEL =========");
            System.out.println("1. Delivery Agent Management");
            System.out.println("2. Customer Management");
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
                case 1 -> agentSubMenu();
                case 2 -> customerSubMenu();
                case 3 -> categorySubMenu();
                case 4 -> menuSubMenu();
                case 5 -> orderSubMenu();
                case 6 -> analyticsSubMenu();
                case 7 -> financialSubMenu();
                default -> System.out.println("Invalid Selection.");
            }
        }
    }

    // --- 1. AGENT SUBMENU ---
    private void agentSubMenu() {
        while (true) {
            System.out.println("\n--- AGENT MANAGEMENT ---");
            System.out.println("1. Add Delivery Agent\n2. List All Agents\n3. Update Status\n0. BACK");
            int choice = ValidationUtil.getValidInt(sc , "choice" , 0);
            if (choice == 0) break;
            switch (choice) {
                case 1 -> {
                    System.out.println("--- Register New Agent ---");
//                    System.out.print("ID: ");
//                    String id = ValidationUtil.getValidId(sc);
                    System.out.print("Name: ");
                    String name = ValidationUtil.getValidName(sc);
                    System.out.print("Phone: ");
                    String phone = ValidationUtil.getValidPhone(sc);
                    System.out.print("Email: ");
                    String email = ValidationUtil.getValidEmail(sc);
                    System.out.print("Vehicle Number: ");
                    String vehicle = ValidationUtil.getValidId(sc);

                    String id = "A-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();

                    DeliveryAgent newAgent = new DeliveryAgent.Builder()
                            .setId(id)
                            .setName(name)
                            .setPhoneNumber(phone)
                            .setEmail(email)
                            .setVehicleNumber(vehicle)
                            .setAvailable(true)
                            .setEarnings(0.0)
                            .build();

                    facade.getAdminService().addDeliveryAgent(newAgent);
                    System.out.println("Agent " + name + " successfully registered!");
                }

                case 2 -> {
                    System.out.println("\n--- DELIVERY AGENT LIST ---");
                    System.out.println("=".repeat(120));
                    System.out.printf("| %-10s | %-15s | %-12s | %-25s | %-12s | %-10s | %-10s | %-10s |%n",
                            "ID", "NAME", "PHONE", "EMAIL", "VEHICLE", "STATUS", "EARNINGS", "TOTAL");
                    System.out.println("=".repeat(120));

                    facade.getAdminService().listAllAgents().forEach(System.out::println);

                    System.out.println("=".repeat(120));
                }

                case 3 -> {
                    System.out.print("Agent ID: ");
                    String aid = ValidationUtil.getValidId(sc);
                    System.out.print("Available (true/false): ");
                    boolean status = ValidationUtil.getValidBoolean(sc);
                    facade.getAdminService().updateAgentStatus(aid, status);
                }
            }
        }
    }

    // --- 2. CUSTOMER SUBMENU ---
    private void customerSubMenu() {
        while (true) {
            System.out.println("\n--- CUSTOMER MANAGEMENT ---");
            System.out.println("1. List All Customers\n2. Remove Customer\n0. BACK");
            int choice = ValidationUtil.getValidInt(sc , "choice" , 0);
            if (choice == 0) break;
            switch (choice) {
                case 1 -> {
                    System.out.println("\n--- CUSTOMER LIST ---");
                    System.out.println("=".repeat(100));
                    System.out.printf("| %-10s | %-15s | %-12s | %-20s | %-20s | %-10s |%n",
                            "ID", "NAME", "USERNAME", "EMAIL", "ADDRESS", "PHONE");
                    System.out.println("=".repeat(100));

                    facade.getAdminService().listCustomers().forEach(System.out::println);

                    System.out.println("=".repeat(100));
                }

                case 2 -> {
                    System.out.print("Customer ID to remove: ");
                    facade.getAdminService().removeCustomer(ValidationUtil.getValidId(sc));
                }
            }
        }
    }

    // --- 3. CATEGORY SUBMENU ---
    private void categorySubMenu() {
        while (true) {
            System.out.println("\n--- CATEGORY MANAGEMENT ---");
            System.out.println("1. View All Categories");
            System.out.println("2. Add Category");
            System.out.println("3. Remove an Item");
            System.out.println("4. Remove All Items from Category");
            System.out.println("0. BACK");
            System.out.print("Choice: ");

            int choice = ValidationUtil.getValidInt(sc , "choice" , 0);
            if (choice == 0) break;

            switch (choice) {
                case 1 -> facade.getAdminService().listCategories();

                case 2 -> handleAddCategory();

                case 3 -> {
                    viewCurrentMenu();

                    System.out.println("\n--- REMOVE MENU ITEM ---");
                    System.out.print("Enter Item ID to remove: ");
                    String id = ValidationUtil.getValidId(sc);

                    MenuItem item = facade.getAdminService().getItemById(id);

                    if (item != null) {
                        System.out.println("Found: " + item.getName() + " [" + item.getCategory() + "]");
                        System.out.print("Are you sure you want to delete this item? (yes/no): ");

                        if (ValidationUtil.getValidBoolean(sc)) {
                            boolean deleted = facade.getAdminService().removeItemById(id);
                            if (deleted) {
                                System.out.println("Item successfully removed from menu.");
                            }
                        } else {
                            System.out.println("CANCELLED...Deletion aborted.");
                        }
                    } else {
                        try {
                            throw new ResourceNotFoundException("ERROR : No item found with ID: " + id);
                        } catch (ResourceNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                case 4 -> {
                    ItemCategoryType selected = selectCategory();
                    System.out.print("Are you sure you want to clear all " + selected.name() + " items? (yes/no): ");

                    if (ValidationUtil.getValidBoolean(sc)) {
                        facade.getAdminService().removeCategoryItems(selected);
                        System.out.println("All " + selected.name() + " items have been removed.");
                    } else {
                        System.out.println("Canceled...Operation aborted.");
                    }
                }
            }
        }
    }

    private ItemCategoryType selectCategory() {
        ItemCategoryType[] categories = ItemCategoryType.values();
        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + ". " + categories[i].name());
        }
        System.out.print("Select Category: ");
        int choice = ValidationUtil.getValidInt(sc , "Category" , 1);
        if (choice > 0 && choice <= categories.length) {
            return categories[choice - 1];
        }
        return ItemCategoryType.FAST_FOOD;  // by default set to fast_food category
    }

    private void handleAddCategory() {
        System.out.print("Enter New Category Name: ");
        String newCat = ValidationUtil.getValidString(sc , "Category").toUpperCase();

        try {
            facade.getAdminService().addCategory(newCat);

            System.out.println("Category " + newCat + " successfully added to system.");
        } catch (DuplicateCategoryException e) {
            System.err.println("Exception : " + e.getMessage());
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
            System.out.println("0. BACK");
            System.out.print("Choice: ");

            int choice = ValidationUtil.getValidInt(sc , "choice" , 0);
            if (choice == 0) break;

            switch (choice) {
                case 1 -> handleAddItem();
                case 2 -> viewCurrentMenu();
            }
        }
    }

    private void handleAddItem(){
        System.out.println("\n--- Add New Food Item ---");

        System.out.print("Enter Item Name: ");
        String name = ValidationUtil.getValidString(sc, "Item Name");

        System.out.print("Enter Base Price: ");
        double price = ValidationUtil.getValidDouble(sc, "Price");

        System.out.print("Enter Initial Stock: ");
        int stock = ValidationUtil.getValidInt(sc, "Stock", 0);
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
                .stock(stock)
                .category(category)
                .isAvailable(stock > 0)
                .build();

        facade.getAdminService().addMenuItem(newItem);
        System.out.println("\nItem successfully added with generated ID: " + autoId);
    }

    private void viewCurrentMenu(){
        List<MenuItem> menu = facade.getAdminService().listAllMenuItems();

        if (menu.isEmpty()) {
            System.out.println("\n[!] The menu is currently empty. Add items first.");
            return;
        }
        System.out.println("\n" + "=".repeat(95));
        System.out.printf("| %-10s | %-18s | %-15s | %-8s | %-11s | %-11s | %-12s |%n",
                "ID", "ITEM NAME", "CATEGORY", "STOCK", "BASE UNIT", "FINAL PRICE" , "GRAND TOTAL");
        System.out.println("-".repeat(95));

        menu.forEach(MenuItem::display);

        System.out.println("=".repeat(95));
    }


    // --- 5. ORDER SUBMENU ---
    private void orderSubMenu() {
        while (true) {
            System.out.println("\n--- ORDER MANAGEMENT ---");
            System.out.println("1. View All Orders\n2. Assign Agent to Pending Order\n3. View Specific User History\n4. View Notifications\n0. BACK");
            int choice = ValidationUtil.getValidInt(sc , "choice" , 0);
            if (choice == 0) break;
            switch (choice) {
                case 1 -> {
                    System.out.println("\n--- ORDER LIST ---");
                    System.out.println("=".repeat(120));
                    System.out.printf("| %-6s | %-12s | %-12s | %-12s | %-10s | %-12s | %-12s | %-20s |%n",
                            "ID", "CUSTOMER", "AGENT", "TOTAL", "DISCOUNT", "FINAL AMT", "STATUS", "CREATED AT");
                    System.out.println("=".repeat(120));

                    facade.getAdminService().getAllOrders().forEach(System.out::println);

                    System.out.println("=".repeat(120));
                }
                case 2 ->{
                    System.out.print("Enter Order ID to assign: ");
                    int oid = ValidationUtil.getValidInt(sc , "Order ID" , 1);
                    try {
                        facade.getOrderService().assignOrderToAgent(oid);
                    } catch (ResourceNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 3 -> {
                    System.out.print("Customer ID: ");
                    System.out.println("\n--- ORDER LIST ---");
                    System.out.println("=".repeat(120));
                    System.out.printf("| %-6s | %-12s | %-12s | %-12s | %-10s | %-12s | %-12s | %-20s |%n",
                            "ID", "CUSTOMER", "AGENT", "TOTAL", "DISCOUNT", "FINAL AMT", "STATUS", "CREATED AT");
                    System.out.println("=".repeat(120));

                    facade.getAdminService().getUserOrderHistory(sc.next()).forEach(System.out::println);

                    System.out.println("=".repeat(120));
                }
//                case 4-> pending....

            }
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
                case 1 -> facade.getAdminService().getMostFrequentItems(5).forEach(e ->
                        System.out.println(e.getKey() + ": " + e.getValue() + " orders"));
                case 2 -> facade.getAdminService().getMostFrequentCustomers(5).forEach(e ->
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