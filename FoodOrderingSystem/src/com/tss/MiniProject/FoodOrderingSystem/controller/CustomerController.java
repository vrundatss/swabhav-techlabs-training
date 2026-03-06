package com.tss.MiniProject.FoodOrderingSystem.controller;

import com.tss.MiniProject.FoodOrderingSystem.exceptions.EmailAlreadyExistsException;
import com.tss.MiniProject.FoodOrderingSystem.exceptions.PhoneNumberAlreadyExistsException;
import com.tss.MiniProject.FoodOrderingSystem.exceptions.ResourceNotFoundException;
import com.tss.MiniProject.FoodOrderingSystem.model.Customer.Customer;
import com.tss.MiniProject.FoodOrderingSystem.model.DeliveryAgent.DeliveryAgent;
import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.MenuItem;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.CancelledStatus;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.Order;
import com.tss.MiniProject.FoodOrderingSystem.model.Payment.Payment;
import com.tss.MiniProject.FoodOrderingSystem.model.discount.DayBasedDiscountStrategy;
import com.tss.MiniProject.FoodOrderingSystem.model.discount.DiscountStrategy;
import com.tss.MiniProject.FoodOrderingSystem.model.discount.FestivalDiscountStrategy;
import com.tss.MiniProject.FoodOrderingSystem.model.discount.FlatBillDiscountStrategy;
import com.tss.MiniProject.FoodOrderingSystem.model.enums.ItemCategoryType;
import com.tss.MiniProject.FoodOrderingSystem.util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.*;
import static com.tss.MiniProject.FoodOrderingSystem.util.ColorUtils.*;


public class CustomerController {
    private final AppFacade facade;
    private final Scanner sc;
    private Customer currentCustomer = null;

    public CustomerController(AppFacade facade, Scanner sc) {
        this.facade = facade;
        this.sc = sc;
    }

    public void authMenu() {
        sc.nextLine();
        while (true) {
            System.out.println("\n===== CUSTOMER PANEL =====");
            System.out.println("1. Login");
            System.out.println("2. Sign Up (For New Customer)");
            System.out.println("3. Logout");
            System.out.print("Selection: ");

            int choice = ValidationUtil.getValidInt(sc , "choice" , 1);
            if (choice == 3) break;

            switch (choice) {
                case 1 -> {
                    handleLogin();
                    if (currentCustomer != null) {
                        showMainMenu();
//                currentCustomer = null; // for logout
                    }
                }
                case 2 -> handleSignup();
                default -> System.out.println("Invalid selection.");
            }


        }
    }

    private void handleLogin() {
        System.out.println("\n--- Customer Login ---");
        System.out.print("Enter Username: ");
        String user = ValidationUtil.getValidUsername(sc);

        System.out.print("Enter Password: ");
        String pass = ValidationUtil.getValidPassword(sc);

        Customer customer = facade.getAuthService().loginCustomer(user, pass);

        if (customer != null) {
            this.currentCustomer = customer;
            System.out.println("Login Successful! Welcome, " + customer.getName());

            //enable notification after login
            facade.getNotificationService().subscribeCustomer(currentCustomer);
        } else {
            System.out.println("Invalid Username or Password...");
        }
    }

    private void handleSignup() {
        System.out.println("\n--- Create Customer Account ---");
        System.out.print("Name: ");
        String name = ValidationUtil.getValidName(sc);
        System.out.print("Username: ");
        String user = ValidationUtil.getValidUsername(sc);
        System.out.print("Password: ");
        String pass = ValidationUtil.getValidPassword(sc);
        System.out.print("Email: ");
        String email = ValidationUtil.getValidEmail(sc);
        System.out.print("Phone Number: ");
        String phone = ValidationUtil.getValidPhone(sc);
        System.out.print("Address: ");
        String address = ValidationUtil.getValidString(sc , "Address");
        try {

            Customer c = facade.getAuthService().registerCustomer(name, user, email, pass,address , phone);
            System.out.println("Registration Successful! Your ID: " + c.getId());

        }catch (EmailAlreadyExistsException | PhoneNumberAlreadyExistsException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }

    private void showMainMenu() {
        while (true) {
            System.out.println("\n--- CUSTOMER MAIN MENU ---");
            System.out.println("1. Browse Menu & Add to Cart");
            System.out.println("2. View Cart & Checkout");
            System.out.println("3. View Order History & Track");
            System.out.println("4. View Notifications");
            System.out.println("0. BACK");
            System.out.print("Selection: ");

            int choice = ValidationUtil.getValidInt(sc , "choice"  , 0);
            if (choice == 0) break;

            switch (choice) {
                case 1 -> browsingSubMenu();
                case 2 -> cartSubMenu();
                case 3 -> historySubMenu();
                case 4 -> notificationMenu();
                default -> System.out.println("Invalid selection.");
            }
        }
    }

    // --- SUBMENU 1: BROWSING ---
    private void browsingSubMenu() {
        while (true) {
            System.out.println("\n--- BROWSE MENU ---");
            System.out.println("1. View All Items");
            System.out.println("2. View by Category");
            System.out.println("3. Add Item to Cart");
            System.out.println("0. BACK");
            int choice = ValidationUtil.getValidInt(sc , "choice"  , 0);
            if (choice == 0) break;

            switch (choice) {
                case 1 -> viewMenuForCustomer();
                case 2 -> facade.getCustomerService().viewByCategory();
                case 3 -> handleAddToCart();
            }
        }
    }

    public void viewMenuForCustomer() {

        //  all menu items
        List<MenuItem> menu = facade.getMenuService().listAllMenuItems();

        //   group by category
        Map<ItemCategoryType, List<MenuItem>> groupedMenu = menu.stream()
                .filter(MenuItem::isAvailable)
                .filter(item -> item.getCategory() != null) // ignore dynamic or null categories
                .collect(Collectors.groupingBy(MenuItem::getCategory));

        // handle empty menu
        if (groupedMenu.isEmpty()) {
            System.out.println("\u001B[33m" + "Sorry! The menu is currently unavailable." + "\u001B[0m");
            return;
        }

        System.out.println("\n" + "═".repeat(75));
        System.out.printf("\u001B[1;37m %-50s %n\u001B[0m", "FOOD MENU");
        System.out.println("═".repeat(75));

        groupedMenu.forEach((category, items) -> {
            System.out.println("\n" + "\u001B[35m" + category.name() + "\u001B[0m");
            System.out.println("─".repeat(70));
            System.out.printf("| %-12s | %-40s | %-10s |%n", "ID", "NAME", "PRICE");

            for (MenuItem item : items) {
                System.out.printf("| %-12s | %-40s | ₹%-8.2f |%n",
                        item.getId(),
                        item.getName(),
                        item.getPrice());
            }
        });

        System.out.println("═".repeat(75));
    }

    // --- SUBMENU 2: CART & CHECKOUT ---
    private void cartSubMenu() {
        while (true) {
            Map<String, Integer> cart = facade.getCartService().getCart(currentCustomer.getId());
            if (cart.isEmpty()) {
                System.out.println("\n--- YOUR CART ---");
                System.out.println("\u001B[33mYour cart is empty. Please add items from the menu.\u001B[0m");
                break;
            }

            // --- CART DISPLAY ---
            System.out.println("\n" + "=".repeat(60));
            System.out.printf("\u001B[36m| %-10s | %-25s | %-5s | %-10s |%n\u001B[0m", "ID", "ITEM", "QTY", "SUBTOTAL");
            System.out.println("-".repeat(60));

            double total = 0;
            for (var entry : cart.entrySet()) {
                MenuItem item = facade.getMenuService().getItemById(entry.getKey());
                if (item != null) {
                    double sub = item.getPrice() * entry.getValue();
                    total += sub;
                    System.out.printf("| %-10s | %-25s | %-5d | ₹%-9.2f |%n",
                            item.getId(), item.getName(), entry.getValue(), sub);
                }
            }
            System.out.println("=".repeat(60));
            System.out.printf("\u001B[1m%-45s ₹%-10.2f%n\u001B[0m", "GROSS TOTAL:", total);

            System.out.println("\n1. Apply Discount & Checkout");
            System.out.println("2. Checkout without Discount");
            System.out.println("3. Remove Specific Item from Cart");
            System.out.println("4. Clear Entire Cart");
            System.out.println("0. BACK");
            int choice = ValidationUtil.getValidInt(sc, "Choice", 0);
            if (choice == 0) break;

            if (choice == 3) {
                System.out.print("Enter Item ID to remove: ");
                String rid = ValidationUtil.getValidId(sc);
                facade.getCartService().removeFromCart(currentCustomer.getId(), rid);
                continue; // to view modified cart again
            }

            if (choice == 4) {
                facade.getCartService().clearCart(currentCustomer.getId());
                break;
            }

            // --- CHECKOUT MANAGEMENT ---

            double discountAmount = 0.0;
            String discountName = "No Discount";

            if (choice == 1) {
                DiscountStrategy strategy = selectActiveDiscountStrategy(total);
                if (strategy != null) {
                    discountAmount = strategy.applyDiscount(total);
                    discountName = strategy.getDescription();
                    System.out.printf("\n\u001B[32m%s Applied! You saved ₹%.2f\u001B[0m%n", discountName, discountAmount);
                }
            }

            double finalPayable = total - discountAmount;

            try {
                // create order
                Order order = facade.getCustomerService()
                        .checkout(currentCustomer.getId(), cart, total, discountAmount, finalPayable, discountName);

//                order.setAddress(currentCustomer.getAddress());

                System.out.println("Deliver to saved address: " + currentCustomer.getAddress());
                System.out.print("Use this address? (yes/no): ");
                if (!ValidationUtil.getValidBoolean(sc)) {
                    System.out.print("Enter new delivery address: ");
                    String newAddress = ValidationUtil.getValidString(sc, "Delivery Address");
                    order.setAddress(newAddress);
                } else {
                    order.setAddress(currentCustomer.getAddress());
                }


                // capture the payment result
                boolean paymentSuccess = facade.getPaymentService().handlePayment(sc, order);

                // proceed if payment was successful
                if (paymentSuccess) {
                    // clear cart after successful money collection
                    facade.getCartService().clearCart(currentCustomer.getId());

                    System.out.println("\n\u001B[32mPayment successful! Generating your invoice...\u001B[0m");

                    Payment payment = facade.getPaymentService().findPaymentByOrderId(order.getId());
                    DeliveryAgent agent = facade.getAdminService().getDeliveryAgentById(order.getDeliveryAgentId());

                    facade.getInvoiceService().printInvoice(order, payment, agent);

                    // now send notifications
                    facade.getNotificationService().notifyUser(currentCustomer,
                            "Order placed successfully...Your order #" + order.getId() + " is being prepared.");

                    facade.getNotificationService().notifyAdmins("New order #" + order.getId() + " placed by " + currentCustomer.getUsername());
                    facade.getNotificationService().notifyAgents("New order available for delivery...");

                    break;
                } else {

                    // if payment was cancelled remove the order from the list because it wasn't paid for
                    facade.getOrderService().removeOrder(order.getId());

                    System.out.println("\u001B[33mPayment was not completed. Your items are still in the cart.\u001B[0m");
                }

            } catch (Exception e) {
                System.out.println("\u001B[31mCheckout Failed: " + e.getMessage() + "\u001B[0m");
            }
        }
    }

    private DiscountStrategy selectActiveDiscountStrategy(double total) {
        List<DiscountStrategy> activeDiscounts = new ArrayList<>();

        FestivalDiscountStrategy festival = new FestivalDiscountStrategy();
        if (festival.isApplicable(total))
            activeDiscounts.add(festival);

        DayBasedDiscountStrategy day = new DayBasedDiscountStrategy();
        if (day.isApplicable(total))
            activeDiscounts.add(day);

        FlatBillDiscountStrategy bill = new FlatBillDiscountStrategy();
        if (bill.isApplicable(total))
            activeDiscounts.add(bill);

        if (activeDiscounts.isEmpty()) {
            return null;
        }

        System.out.println("\n--- CURRENTLY AVAILABLE DISCOUNTS ---");
        for (int i = 0; i < activeDiscounts.size(); i++) {
            System.out.println((i + 1) + ". " + activeDiscounts.get(i).getDescription());
        }
        System.out.println("0. BACK");
        System.out.print("Choice: ");
        int choice = ValidationUtil.getValidInt(sc , "choice"  , 0);

        if (choice == 0 || choice > activeDiscounts.size()) return null;
        return activeDiscounts.get(choice - 1);
    }

    private void handleAddToCart() {

        viewMenuForCustomer();

        System.out.print("Enter Item ID: ");
        String itemId = ValidationUtil.getValidId(sc);

        MenuItem item = facade.getMenuService().getItemById(itemId);

        if (item != null) {
            System.out.print("Enter Quantity: ");
            int qty = ValidationUtil.getValidQty(sc, "Quantity", 1 , 100);

            facade.getCartService().addToCart(currentCustomer.getId(), itemId, qty);
            System.out.println("Item Successfully added to cart.");
        } else {
            try {
                throw new ResourceNotFoundException("Item not found.");
            }catch (ResourceNotFoundException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void notificationMenu() {
        System.out.println("\n--- INBOX ---");
        var inbox = currentCustomer.getNotifications();

        if (inbox.isEmpty()) {
            System.out.println("No new notifications.");
        } else {
            inbox.forEach(System.out::println);
            System.out.print("\nClear all notifications? (y/n): ");
            if(ValidationUtil.getValidBoolean(sc))
                inbox.clear();
//            if (sc.nextLine().equalsIgnoreCase("y"))
//                inbox.clear();
        }
    }

    // --- SUBMENU 3: HISTORY & TRACKING ---
    private void historySubMenu() {
        while (true) {
            var history = facade.getAdminService().getUserOrderHistory(currentCustomer.getId());

            System.out.println("\n--- MY ORDERS ---");
            if (history.isEmpty()) {
                System.out.println("No orders found.");
                break;
            }

            System.out.printf("%-5s | %-30s | %-12s | %-10s | %-10s%n", "ID", "ITEMS", "STATUS", "TOTAL", "DATE");
            System.out.println("-".repeat(85));

            for (Order o : history) {

                // to convert ID map into a String of Names
                String itemNames = o.getItemIdQty().keySet().stream()
                        .map(id -> {
                            MenuItem item = facade.getMenuService().getItemById(id);
                            return (item != null) ? item.getName() : "Unknown Item";
                        })
                        .collect(Collectors.joining(", "));

                System.out.printf("#%-4d | %-30s | %-12s | ₹%-9.2f | %-10s%n",
                        o.getId(),
                        itemNames,
                        o.getStatus().getName(),
                        o.getFinalAmount(),
                        o.getCreatedAt().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }

            System.out.println("\n1. View Full Details / Order Tracking");
            System.out.println("2. Cancel Order (Only if Pending and Not Assigned)");
            System.out.println("0. BACK");
            System.out.print("Selection: ");
            int choice = ValidationUtil.getValidInt(sc, "Selection", 0);

            if (choice == 0) break;
            if (choice == 1) facade.getOrderService().handleTrackOrder(sc , currentCustomer);
            if (choice == 2) facade.getOrderService().handleCancelOrder(sc , currentCustomer);
        }
    }

}