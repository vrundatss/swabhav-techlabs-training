package FoodOrderingSystem.controller;

import com.tss.MiniProject.FoodOrderingSystem.model.DeliveryAgent.DeliveryAgent;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.Order;
import com.tss.MiniProject.FoodOrderingSystem.util.ValidationUtil;

import java.util.List;
import java.util.Scanner;

public class DeliveryAgentController {
    private final AppFacade facade;
    private final Scanner sc;
    private DeliveryAgent currentAgent = null;

    public DeliveryAgentController(AppFacade facade, Scanner sc) {
        this.facade = facade;
        this.sc = sc;
    }

    public void authMenu() {
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
        sc.nextLine();

        System.out.print("Enter Email: ");
        String email =ValidationUtil.getValidEmail(sc);

        System.out.print("Enter Password: ");
        String password = ValidationUtil.getValidPassword(sc);

        DeliveryAgent agent = facade.getAuthService().loginDeliveryAgent(email, password);

        if (agent != null) {
            currentAgent = agent;
            System.out.println("Login Successful! Welcome, " + agent.getName());
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
        System.out.print("Address: ");
        String address = ValidationUtil.getValidString(sc , "Address");
        System.out.print("Vehicle Number: ");
        String vehicle = ValidationUtil.getValidString(sc , "Vehicle number");
        System.out.print("Password: ");
        String password = ValidationUtil.getValidPassword(sc);

        try {
            facade.getAuthService().registerDeliveryAgent(name, email, address, vehicle, password);
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
            System.out.println("0. BACK");
            System.out.print("Selection: ");

            int choice = ValidationUtil.getValidInt(sc , "choice" , 0);
            if (choice == 0) break;

            switch (choice) {
                case 1 -> tasksSubMenu();
                case 2 -> walletSubMenu();
                case 3 -> toggleAvailability();
                default -> System.out.println("Invalid selection.");
            }
        }
    }

    // --- SUBMENU 1: TASKS & ORDER MANAGEMENT ---
    private void tasksSubMenu() {
        while (true) {
            System.out.println("\n--- ACTIVE ASSIGNMENTS ---");
            List<Order> assigned = facade.getDeliveryService().getPendingOrdersForAgent(currentAgent.getId());

            if (assigned.isEmpty()) {
                System.out.println("No pending orders assigned to you.");
                break;
            }

            assigned.forEach(o -> System.out.println("Order ID: " + o.getId() + " | Status: " + o.getStatus().getName() + " | Amt: " + o.getFinalAmount()));

            System.out.println("\n1. Accept & Mark Out-for-Delivery");
            System.out.println("2. Mark as Delivered (Finalize)");
            System.out.println("3. Cancel/Reject Assignment");
            System.out.println("0. BACK");
            int choice = ValidationUtil.getValidInt(sc , "choice" , 0);
            if (choice == 0) break;

            System.out.print("Enter Order ID: ");
            int oid = ValidationUtil.getValidInt(sc , "Order id", 0);

            try {
                switch (choice) {
                    case 1 -> facade.getDeliveryService().acceptOrder(currentAgent.getId(), oid);
                    case 2 -> facade.getDeliveryService().markDelivered(currentAgent.getId(), oid);
                    case 3 -> facade.getDeliveryService().cancelOrder(currentAgent.getId(), oid);
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
                        .forEach(o -> System.out.println("Order #" + o.getId() + " | Flat Payout: ₹300.00"));
            }
        }
    }

    private void toggleAvailability() {
        boolean currentStatus = currentAgent.getAvailable();
        facade.getAdminService().updateAgentStatus(currentAgent.getId(), !currentStatus);
        System.out.println("Status toggled to: " + (!currentStatus ? "Online" : "Offline"));
    }
}