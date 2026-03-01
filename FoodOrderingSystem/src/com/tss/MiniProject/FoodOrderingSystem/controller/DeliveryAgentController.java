package com.tss.MiniProject.FoodOrderingSystem.controller;

import com.tss.MiniProject.FoodOrderingSystem.model.DeliveryAgent.DeliveryAgent;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.Order;
import com.tss.MiniProject.FoodOrderingSystem.util.ValidationUtil;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DeliveryAgentController {
    private final AppFacade facade;
    private final Scanner sc;
    private DeliveryAgent currentAgent = null;

    public DeliveryAgentController(AppFacade facade, Scanner sc) {
        this.facade = facade;
        this.sc = sc;
    }

    public void authMenu() {
        sc.nextLine();
        while (true) {
            System.out.println("\n--- DELIVERY AGENT ACCESS ---");
            System.out.println("1. Agent Login");
            System.out.println("2. Agent Registration");
            System.out.println("3. Logout");
            System.out.println("0. BACK");
            System.out.print("Selection: ");

            int choice = ValidationUtil.getValidInt(sc , "choice" , 0);
            if (choice == 0 || choice == 3) break;

            switch (choice) {
                case 1 -> handleLogin();
                case 2 -> handleSignup();
                default -> System.out.println("Invalid selection.");
            }

            if (currentAgent != null) {
                showMainMenu();
                currentAgent = null; // it will reset the session on logout
            }
        }
    }


    private void handleLogin() {

        System.out.print("Enter Email: ");
        String email =ValidationUtil.getValidEmail(sc);

        System.out.print("Enter Password: ");
        String password = ValidationUtil.getValidPassword(sc);

        DeliveryAgent agent = facade.getAuthService().loginDeliveryAgent(email, password);

        if (agent != null) {
            currentAgent = agent;
            System.out.println("Login Successful! Welcome, " + agent.getName());

            // enable notifications
            facade.getNotificationService().subscribeAgent(currentAgent);

            showMainMenu();
        } else {
            System.out.println("Invalid Email or Password.");
        }
    }

    private void handleSignup() {
        System.out.print("Name: ");
        String name = ValidationUtil.getValidName(sc);
        System.out.print("Email: ");
        String email = ValidationUtil.getValidEmail(sc);
        System.out.print("Phone: ");
        String phone = ValidationUtil.getValidPhone(sc);
        System.out.print("Address: ");
        String address = ValidationUtil.getValidString(sc , "Address");
        System.out.print("Vehicle Number: ");
        String vehicle = ValidationUtil.getValidString(sc , "Vehicle number");
        System.out.print("Password: ");
        String password = ValidationUtil.getValidPassword(sc);

        try {
            facade.getAuthService().registerDeliveryAgent(name, email,phone, address, vehicle, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showMainMenu() {
        while (true) {
            System.out.println("\n--- AGENT DASHBOARD ---");
            System.out.println("Status: " + (currentAgent.getAvailable() ? "Online/Free" : "Busy/On-Delivery"));
            System.out.println("1. Tasks: Assigned Orders");
            System.out.println("2. Wallet: Earnings & History");
            System.out.println("3. Profile: Availability Toggle");
            System.out.println("4. View Notifications");
            System.out.println("0. BACK");
            System.out.print("Selection: ");

            int choice = ValidationUtil.getValidInt(sc , "choice" , 0);
            if (choice == 0) break;

            switch (choice) {
                case 1 -> tasksSubMenu();
                case 2 -> walletSubMenu();
                case 3 -> toggleAvailability();
                case 4->  notificationMenu();
                default -> System.out.println("Invalid selection.");
            }
        }
    }

    // TASKS and ORDER MANAGEMENT

    private void tasksSubMenu() {
        while (true) {
            System.out.println("\n" + "═".repeat(90));
            System.out.println("          ACTIVE ASSIGNMENTS");
            System.out.println("═".repeat(90));

            List<Order> assigned = facade.getDeliveryService().getPendingOrdersForAgent(currentAgent.getId());

            if (assigned.isEmpty()) {
                System.out.println("No pending orders assigned to you.");
                break;
            }

            for (Order o : assigned) {
                var customer = facade.getCustomerService().getCustomerById(o.getCustomerId());
                String custName = (customer != null) ? customer.getUsername() : "Unknown";
                String custPhone = (customer != null) ? customer.getPhoneNumber() : "N/A";
                String custAddress = (o.getAddress() != null) ? o.getAddress(): "Address not available";

                String itemList = o.getItemIdQty().entrySet().stream()
                        .map(entry -> {
                            var item = facade.getMenuService().getItemById(entry.getKey());
                            return (item != null ? item.getName() : "Unknown") + " (" + entry.getValue() + ")";
                        })
                        .collect(Collectors.joining(", "));

                // --- Payment Info ---
                String payMethod = (o.getPaymentMethod() != null) ? o.getPaymentMethod() : "Not Set";
//                String payStatus = (o.getPaymentStatus() != null) ? o.getPaymentStatus() : "Unknown";

                System.out.println("Order ID       : " + o.getId());
                System.out.println("Status         : " + o.getStatus().getName());
                System.out.println("Amount         : ₹" + o.getFinalAmount());
                System.out.println("Payment Method : " + payMethod);
//                System.out.println("Payment Status : " + (payStatus.equalsIgnoreCase("PAID")
//                        ? "\u001B[32mPAID\u001B[0m"
//                        : "\u001B[33mPENDING\u001B[0m"));
                System.out.println("Customer ID    : " + o.getCustomerId());
                System.out.println("Customer       : " + custName);
                System.out.println("Contact No.    : " + custPhone);
                System.out.println("Address        : " + custAddress);
                System.out.println("Items          : " + itemList);
                System.out.println("-".repeat(90));
            }

            System.out.println("\n1. Accept & Mark Out-for-Delivery");
            System.out.println("2. Mark as Delivered");
            System.out.println("0. BACK");
            System.out.print("Selection: ");
            int choice = ValidationUtil.getValidInt(sc, "choice", 0);
            if (choice == 0) break;

            System.out.print("Enter Order ID: ");
            int oid = ValidationUtil.getValidInt(sc, "Order ID", 0);

            try {
                switch (choice) {
                    case 1 -> facade.getDeliveryService().acceptOrder(currentAgent.getId(), oid);
                    case 2 -> facade.getDeliveryService().markDelivered(currentAgent.getId(), oid);
                    default -> System.out.println("Invalid selection.");
                }
            } catch (Exception e) {
                System.out.println("Action Failed: " + e.getMessage());
            }
        }
    }

    // --- SUBMENU 2: EARNINGS & HISTORY ---
    private void walletSubMenu() {
        while (true) {
            System.out.println("\n--- MY WALLET ---");
            System.out.println("Total Earnings : ₹" + currentAgent.getEarnings());

            System.out.println("\n1. View Delivered Order History");
            System.out.println("0. BACK");
            int choice = ValidationUtil.getValidInt(sc , "choice" , 0);
            if (choice == 0) break;

            if (choice == 1) {
                System.out.println("\n--- Delivered History ---");
                facade.getDeliveryService().getDeliveredHistory(currentAgent.getId())
                        .forEach(o -> System.out.println("Order #" + o.getId() + " | Flat Payout: " + currentAgent.getEarnings()));
            }
        }
    }

    private void toggleAvailability() {
        boolean currentStatus = currentAgent.getAvailable();
        facade.getAdminService().updateAgentStatus(currentAgent.getId(), !currentStatus);
        System.out.println("Status toggled to: " + (!currentStatus ? "Online" : "Offline"));
    }

    private void notificationMenu() {
        System.out.println("\n--- INBOX ---");
        var inbox = currentAgent.getNotifications();

        if (inbox.isEmpty()) {
            System.out.println("No new notifications.");
        } else {
            inbox.forEach(System.out::println);
            System.out.print("\nClear all notifications? (y/n): ");
            if (sc.next().equalsIgnoreCase("y")) inbox.clear();
        }
    }
}