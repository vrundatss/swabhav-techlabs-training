package com.tss.MiniProject.FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.controller.AppFacade;
import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
import com.tss.MiniProject.FoodOrderingSystem.model.Constants.Constant;
import com.tss.MiniProject.FoodOrderingSystem.model.DeliveryAgent.DeliveryAgent;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.*;

import java.util.List;

public class DeliveryService {

    private final DataStore db = DataStore.getInstance();
    AppFacade facade;

    public DeliveryService(AppFacade facade) {
        this.facade = facade;
    }

    public List<Order> getPendingOrdersForAgent(String agentId) {
        return db.getOrders().stream()
                .filter(o -> agentId.equals(o.getDeliveryAgentId()))
                .filter(o -> !(o.getStatus() instanceof DeliveredStatus))
                .filter(o -> !(o.getStatus() instanceof CancelledStatus))
                .toList();
    }


     // agent accepts the order and moves it to OUT_FOR_DELIVERY status.

    public void acceptOrder(String agentId, int orderId) {
        Order order = findOrder(orderId);

        if (!agentId.equals(order.getDeliveryAgentId())) {
            throw new IllegalArgumentException("Order not assigned to you");
        }

        //  AssignedStatus ==> OutForDeliveryStatus
        order.setStatus(new OutForDeliveryStatus());
        System.out.println("Order " + orderId + " is now Out for Delivery.");
    }


     //Marks order as DELIVERED, frees the agent, and give 300 rs. per order to agent
     public void markDelivered(String agentId, int orderId) {
         // 1. Find the order in the database
         Order order = db.getOrders().stream()
                 .filter(o -> o.getId() == orderId && o.getDeliveryAgentId().equals(agentId))
                 .findFirst()
                 .orElseThrow(() -> new RuntimeException("Order not found or not assigned to you."));

         // 2. from OUT_FOR_DELIVERY to DELIVERED
         order.nextState();

         // 3. Update Agent Earnings and Availability
         DeliveryAgent agent = db.getAgents().stream()
                 .filter(a -> a.getId().equals(agentId))
                 .findFirst()
                 .orElseThrow(() -> new RuntimeException("Agent not found."));

         // add exactly 300 to the agent's earnings
         agent.setEarnings(agent.getEarnings() + Constant.AGENT_FLAT_PAYOUT);

         // Mark agent as free again
         agent.setAvailable(true);

         System.out.println("Order marked as delivered. " + Constant.AGENT_FLAT_PAYOUT +  " added to your wallet.");

         facade.getNotificationService().notifyUser(
                 facade.getCustomerService().getCustomerById(order.getCustomerId()),
                 "Order #" + order.getId() + " has been successfully delivered!"
         );
     }

    public List<Order> getDeliveredHistory(String agentId) {
        return db.getOrders().stream()
                .filter(o -> agentId.equals(o.getDeliveryAgentId()))
                //instead of equalsIgnoreCase("DELIVERED") we can use instanceof to check the status...

                .filter(o -> o.getStatus() instanceof DeliveredStatus)
                .toList();
    }

    private Order findOrder(int orderId) {
        return db.getOrders().stream()
                .filter(o -> o.getId() == orderId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Order ID " + orderId + " not found"));
    }
}
