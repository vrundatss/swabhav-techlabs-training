package com.tss.MiniProject.FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.controller.AppFacade;
import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
import com.tss.MiniProject.FoodOrderingSystem.exceptions.ResourceNotFoundException;
import com.tss.MiniProject.FoodOrderingSystem.model.Constants.Constant;
import com.tss.MiniProject.FoodOrderingSystem.model.Customer.Customer;
import com.tss.MiniProject.FoodOrderingSystem.model.DeliveryAgent.DeliveryAgent;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.*;
import com.tss.MiniProject.FoodOrderingSystem.util.ValidationUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.*;
import java.util.Scanner;
import static com.tss.MiniProject.FoodOrderingSystem.util.ColorUtils.*;
import java.util.stream.Collectors;


public class OrderService {

    private final DataStore db = DataStore.getInstance();
    AppFacade facade;

    public OrderService(AppFacade facade) {
        this.facade = facade;
    }

    public void viewAllOrders() {
        System.out.println("\n--- ORDER LIST ---");
        System.out.println("=".repeat(150));
        System.out.printf("| %-6s | %-12s | %-30s | %-12s | %-10s | %-12s | %-12s | %-12s | %-20s |%n",
                "ID", "CUSTOMER", "ITEMS (Qty)", "AGENT", "TOTAL", "DISCOUNT", "FINAL AMT", "STATUS", "CREATED AT");
        System.out.println("=".repeat(150));

        List<Order> orders = DataStore.getInstance().getOrders();

        if (orders.isEmpty()) {
            System.out.println(" ".repeat(60) + "No orders found.");
        } else {
            for (Order o : orders) {

                String itemList = o.getItemIdQty().entrySet().stream()
                        .map(entry -> {
                            String itemId = entry.getKey();
                            int qty = entry.getValue();
                            var item = db.getMenuItems().stream()
                                    .filter(m -> m.getId().equalsIgnoreCase(itemId))
                                    .findFirst()
                                    .orElse(null);
                            return (item != null ? item.getName() : "Unknown") + "(" + qty + ")";
                        })
                        .collect(Collectors.joining(", "));

                System.out.printf("| %-6d | %-12s | %-30s | %-12s | ₹%-11.2f | ₹%-9.2f | ₹%-11.2f | %-12s | %-20s |%n",
                        o.getId(),
                        o.getCustomerId(),
                        itemList,
                        (o.getDeliveryAgentId() != null) ? o.getDeliveryAgentId() : "Not Assigned",
                        o.getTotalAmount(),
                        o.getDiscount(),
                        o.getFinalAmount(),
                        o.getStatus().getName(),
                        o.getCreatedAt().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                );
            }
        }

        System.out.println("=".repeat(150));
    }


    public void assignOrderToAgent(int orderId) throws ResourceNotFoundException {

        Order order = db.getOrders().stream()
                .filter(o -> o.getId() == orderId)
                .findFirst()
                .orElse(null);

        if (order == null) {
            throw new ResourceNotFoundException("Order #" + orderId + " not found.");
        }

        // Check if order is in PENDING state
        if (!order.getStatus().getName().equalsIgnoreCase("PENDING")) {
            System.out.println("Only PENDING orders can be assigned. Current status: " + order.getStatus().getName());
            return;
        }

        // 3. Find an available agent
        List<DeliveryAgent> availableAgents = db.getAgents().stream()
                .filter(DeliveryAgent::getAvailable)
                .toList();

        if (availableAgents.isEmpty()) {
            System.out.println("No delivery agents available right now.");
            return;
        }

        // pick one agent randomly
        DeliveryAgent availableAgent = availableAgents.get(new Random().nextInt(availableAgents.size()));

        availableAgent.setAvailable(false); // mark that agent is busy...
        order.setDeliveryAgentId(availableAgent.getId());
        order.nextState(); // moves PENDING to ASSIGNED

        System.out.println("Order #" + orderId + " randomly assigned to Agent " + availableAgent.getName() + " (" + availableAgent.getId() + ")");

        facade.getNotificationService().notifyUser(
                facade.getCustomerService().getCustomerById(order.getCustomerId()),
                "Your order #" + orderId + " has been assigned to delivery partner " + availableAgent.getName() + "."
        );

        // notify agent about assigned order
        var customer = facade.getCustomerService().getCustomerById(order.getCustomerId());
        String customerName = (customer != null) ? customer.getUsername() : "Customer";
        String address = (order.getAddress() != null) ? order.getAddress() : "N/A";

        facade.getNotificationService().notifyUser(
                availableAgent,
                "New Order Assigned!\n" +
                        "Order ID: #" + orderId + "\n" +
                        "Customer: " + customerName + "\n" +
                        "Amount: ₹" + order.getFinalAmount() + "\n" +
                        "Address: " + address
        );
//
//        facade.getNotificationService().notifyGroup(
//                db.getAdmins(),
//                "Order #" + orderId + " assigned to " + availableAgent.getName() + "."
//        );
    }


    public void handleTrackOrder(Scanner sc, Customer currentCustomer) {
        System.out.print("\n" + CYAN + "Enter Order ID to track: " + RESET);
        int oid = ValidationUtil.getValidInt(sc, "Order ID", 1);

        // Fetch order
        Order order = facade.getAdminService().getUserOrderHistory(currentCustomer.getId())
                .stream()
                .filter(o -> o.getId() == oid)
                .findFirst()
                .orElse(null);

        if (order == null) {
            System.out.println("Order not found." + RESET);
            return;
        }

        // --- HEADER ---
        System.out.println("\n" + "═".repeat(50));
        System.out.println(WHITE_BOLD + "       ORDER TRACKING: #" + order.getId() + RESET);
        System.out.println("═".repeat(50));

        // --- ORDER STATUS ---
        String status = order.getStatus().getName().toUpperCase();
        System.out.println(" Current Status : " + getColoredStatus(status));
        System.out.println(" Order Date     : " + order.getCreatedAt()
                .format(java.time.format.DateTimeFormatter.ofPattern("dd MMM, hh:mm a")));
        System.out.println(" Final Amount   : " + GREEN + "₹" + order.getFinalAmount() + RESET);

        // --- DELIVERY PARTNER SECTION ---
        System.out.println("\n" + WHITE_BOLD + " DELIVERY PARTNER " + RESET);
        System.out.println("-".repeat(50));

        if (order.getDeliveryAgentId() != null) {
            // Fetch agent
            var agent = facade.getAdminService().getDeliveryAgentById(order.getDeliveryAgentId());

            if (agent != null) {
                System.out.println(GREEN + " Assigned Delivery Agent " + RESET);
                System.out.println(" Name    : " + agent.getName());
                System.out.println(" Contact : " + agent.getPhoneNumber());
            } else {
                System.out.println(YELLOW + " Agent assigned, but details not available." + RESET);
            }

        } else {
            // Not assigned yet
            System.out.println(YELLOW + " Status  : Searching for a nearby delivery partner..." + RESET);
            System.out.println(CYAN + " Note    : Your order is confirmed and being prepared!" + RESET);
        }

        System.out.println("═".repeat(50));
    }

    public void handleCancelOrder(Scanner sc, Customer currentCustomer) {
        System.out.print("Enter Order ID to cancel: ");
        int oid = ValidationUtil.getValidInt(sc, "Order ID", 1);

        // Find the order in the current customer's history
        Order order = facade.getAdminService().getUserOrderHistory(currentCustomer.getId())
                .stream()
                .filter(o -> o.getId() == oid)
                .findFirst()
                .orElse(null);

        if (order == null) {
            System.out.println("\u001B[31mOrder not found.\u001B[0m");
            return;
        }

        // REAL-TIME SECURITY CHECK
        // 1. Must be Pending status
        // 2. Must NOT have an agent ID assigned
        boolean isPending = order.getStatus().getName().equalsIgnoreCase("PENDING");
        boolean hasNoAgent = (order.getDeliveryAgentId() == null);

        if (isPending && hasNoAgent) {
            System.out.print("Are you sure you want to cancel Order #" + oid + "? (yes/no): ");
            if (ValidationUtil.getValidBoolean(sc)) {
                // Update status in the system
                order.setStatus(new CancelledStatus());
                System.out.println("\u001B[32mOrder successfully cancelled. Refund initiated.\u001B[0m");

                facade.getNotificationService().notifyUser(currentCustomer,
                        "You cancelled order #" + oid + ". Refund initiated.");
                facade.getNotificationService().notifyAdmins(
                        "Customer " + currentCustomer.getUsername() + " cancelled order #" + oid + ".");
            }
        } else {
            System.out.println("\u001B[31mCannot cancel Order #" + oid + ".");
            System.out.println("Reason ==> Order is already " + order.getStatus().getName() +
                    (hasNoAgent ? "" : " and assigned to an agent.") + "\u001B[0m");
        }
    }

    public void removeOrder(int orderId) {
        db.getOrders().removeIf(o -> o.getId() == orderId);
    }

    private String getColoredStatus(String status) {
        status = status.toUpperCase();

        switch (status) {
            case "PENDING":
                return YELLOW + status + RESET;

            case "ASSIGNED":
                return CYAN + status + RESET;

            case "PICKED_UP":
                return BLUE + status + RESET;

            case "ON_THE_WAY":
                return BLUE_BOLD + status + RESET;

            case "DELIVERED":
                return GREEN + status + RESET;

            case "CANCELLED":
                return RED + status + RESET;

            default:
                return WHITE_BOLD + status + RESET;
        }
    }
}