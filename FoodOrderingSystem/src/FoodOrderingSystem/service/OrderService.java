package FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.controller.AppFacade;
import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
import com.tss.MiniProject.FoodOrderingSystem.exceptions.ResourceNotFoundException;
import com.tss.MiniProject.FoodOrderingSystem.model.Constants.Constant;
import com.tss.MiniProject.FoodOrderingSystem.model.DeliveryAgent.DeliveryAgent;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


public class OrderService {

    private final DataStore db = DataStore.getInstance();
    AppFacade facade;

    public void placeOrder(String  customerId, Map<String, Integer> items, double totalAmount) {
        Order newOrder = new Order();
        newOrder.setCustomerId(customerId);
        newOrder.setItemIdQty(items);
        newOrder.setFinalAmount(totalAmount);

        // initial state = PlacedStatus
        newOrder.setStatus(new PlacedStatus());
        newOrder.setCreatedAt(LocalDateTime.now());

        db.getOrders().add(newOrder);

        // move from Placed ==> Pending
        newOrder.nextState();

        // move from Pending ==> Assigned
        newOrder.nextState();
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
        DeliveryAgent availableAgent = db.getAgents().stream()
                .filter(DeliveryAgent::getAvailable)
                .findFirst()
                .orElse(null);

        if (availableAgent == null) {
            System.out.println("Assignment Failed: No delivery agents are currently available.");
            return;
        }

        // 4. Assign Agent and Advance State
        order.setDeliveryAgentId(availableAgent.getId());
        availableAgent.setAvailable(false); // mark that agent is busy...
        order.nextState(); // moves PENDING to ASSIGNED

        System.out.println("Order #" + orderId + " assigned to Agent " + availableAgent.getName() + " (" + availableAgent.getId() + ")");
    }

    public void completeDelivery(int orderId) {
        Order order = db.getOrders().stream()
                .filter(o -> o.getId() == orderId)
                .findFirst()
                .orElse(null);

        // Only process if the order is currently ASSIGNED
        if (order != null && order.getStatus().getName().equalsIgnoreCase("ASSIGNED")) {

            // 1. Advance status to DELIVERED
            order.nextState();

            // 2. Free up the agent and update earnings
            String agentId = order.getDeliveryAgentId();
            DeliveryAgent agent = db.getAgents().stream()
                    .filter(a -> a.getId().equals(agentId))
                    .findFirst()
                    .orElse(null);

            if (agent != null) {
                agent.setAvailable(true);
                agent.setEarnings(agent.getEarnings() + Constant.AGENT_FLAT_PAYOUT);
            }

            // --- SEPARATE NOTIFICATIONS ---

            // Notify Customer: Their food has arrived
            facade.getNotificationService().notifyUser(
                    db.getCustomerById(order.getCustomerId()),
                    "Enjoy your meal...Order #" + orderId + " has been delivered."
            );

            // Notify Agent: Confirmation of payout
            facade.getNotificationService().notifyUser(
                    agent,
                    "Delivery Confirmed...₹" + Constant.AGENT_FLAT_PAYOUT + " added to your wallet for Order #" + orderId
            );

            // Notify Admins: System log for financial tracking
            facade.getNotificationService().notifyGroup(
                    db.getAdmins(),
                    "Order #" + orderId + " completed. Agent: " + agentId + " | Payout: ₹300"
            );

            System.out.println("Order #" + orderId + " delivered successfully.");
        } else {
            System.out.println("Delivery failed: Order not found or not in 'ASSIGNED' state.");
        }
    }

    public void advanceOrder(int orderId) {
        db.getOrders().stream()
                .filter(o -> o.getId() == orderId)
                .findFirst()
                .ifPresent(Order::nextState);
    }

    public void cancelOrder(int orderId) {
        db.getOrders().stream()
                .filter(o -> o.getId() == orderId)
                .findFirst()
                .ifPresent(Order::cancelOrder);
    }

    public List<Order> getOrdersByStatus(String statusName) {
        return db.getOrders().stream()
                .filter(o -> o.getStatus().getName().equalsIgnoreCase(statusName))
                .toList();
    }

    public double getTotalAdminRevenue() {
        return db.getOrders().stream()
                .filter(o -> o.getStatus().getName().equalsIgnoreCase("DELIVERED"))
                .mapToDouble(o -> o.getFinalAmount() * Constant.ADMIN_COMMISSION)
                .sum();
    }
}

//package com.tss.MiniProject.FoodOrderingSystem.service;
//
//import com.tss.MiniProject.FoodOrderingSystem.model.Order.Order;
//import com.tss.MiniProject.FoodOrderingSystem.repository.OrderRepository;
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class OrderService {
//
//    private final OrderRepository orderRepo;
//
//    public OrderService(OrderRepository orderRepo) {
//        this.orderRepo = orderRepo;
//    }
//
//
//    public List<Order> getOrdersByCustomer(int customerId) {
//        return orderRepo.getAll().stream()
//                .filter(o -> o.getCustomerId() == customerId)
//                .toList();
//    }
//
//    public List<Order> getOrdersByDeliveryAgent(int agentId) {
//        return orderRepo.getAll().stream()
//                .filter(o -> o.getDeliveryAgentId() == agentId)
//                .toList();
//    }
//
//    public List<Order> getOrdersByStatus(String statusName) {
//        return orderRepo.getAll().stream()
//                .filter(o -> o.getStatus().getName().equalsIgnoreCase(statusName))
//                .toList();
//    }
//
//    public double getTotalRevenue() {
//        return orderRepo.getAll().stream()
//                .filter(o -> o.getStatus().getName().equalsIgnoreCase("DELIVERED"))
//                .mapToDouble(Order::getFinalAmount)
//                .sum();
//    }
//
//    public Map<String, Long> getOrderCountByStatus() {
//        return orderRepo.getAll().stream()
//                .collect(Collectors.groupingBy(o -> o.getStatus().getName(), Collectors.counting()));
//    }
//
//    public Optional<Integer> getMostFrequentCustomer() {
//        return orderRepo.getAll().stream()
//                .collect(Collectors.groupingBy(Order::getCustomerId, Collectors.counting()))
//                .entrySet().stream()
//                .max(Map.Entry.comparingByValue())
//                .map(Map.Entry::getKey);
//    }
//}