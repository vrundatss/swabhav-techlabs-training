package FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
import com.tss.MiniProject.FoodOrderingSystem.model.Constants.Constant;
import com.tss.MiniProject.FoodOrderingSystem.model.DeliveryAgent.DeliveryAgent;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.*;

import java.util.List;

public class DeliveryService {

    private final DataStore db = DataStore.getInstance();

//    public List<Order> getPendingOrdersForAgent(String agentId) {
//        return db.getOrders().stream()
//                .filter(o -> agentId.equals(o.getDeliveryAgentId()) &&
//                        !o.getStatus().getName().equalsIgnoreCase("DELIVERED") &&
//                        !o.getStatus().getName().equalsIgnoreCase("CANCELLED"))
//                .toList();
//    }

    public List<Order> getPendingOrdersForAgent(String agentId) {
        return db.getOrders().stream()
                .filter(o -> agentId.equals(o.getDeliveryAgentId()))
                .filter(o -> !(o.getStatus() instanceof DeliveredStatus)) // Check class type
                .filter(o -> !(o.getStatus() instanceof CancelledStatus)) // Check class type
                .toList();
    }


     // Agent accepts the order and moves it to OUT_FOR_DELIVERY status.

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

         System.out.println("Order marked as delivered. ₹500 added to your wallet.");
     }

    public List<Order> getDeliveredHistory(String agentId) {
        return db.getOrders().stream()
                .filter(o -> agentId.equals(o.getDeliveryAgentId()))
                //instead of equalsIgnoreCase("DELIVERED") we can use instanceof to check the status...

                .filter(o -> o.getStatus() instanceof DeliveredStatus)
                .toList();
    }

//     Cancels the order and resets agent availability.
    public void cancelOrder(String agentId, int orderId) {
        Order order = findOrder(orderId);

        if (order.getStatus().getName().equalsIgnoreCase("DELIVERED")) {
            throw new IllegalArgumentException("Cannot cancel a delivered order");
        }

        order.setStatus(new CancelledStatus());

//        // Free the agent in the central list if they were assigned
//        if (order.getDeliveryAgentId() != null) {
//            db.getAgents().stream()
//                    .filter(a -> a.getId().equals(order.getDeliveryAgentId()))
//                    .findFirst()
//                    .ifPresent(a -> a.setAvailable(true));
//        }
    }

    private Order findOrder(int orderId) {
        return db.getOrders().stream()
                .filter(o -> o.getId() == orderId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Order ID " + orderId + " not found"));
    }
}







//package com.tss.MiniProject.FoodOrderingSystem.service;
//
//import com.tss.MiniProject.FoodOrderingSystem.model.DeliveryAgent.DeliveryAgent;
//import com.tss.MiniProject.FoodOrderingSystem.model.Order.Order;
//import com.tss.MiniProject.FoodOrderingSystem.model.Order.OrderStatus;
//import com.tss.MiniProject.FoodOrderingSystem.model.Order.OutForDeliveryStatus;
//import com.tss.MiniProject.FoodOrderingSystem.repository.DeliveryAgentRepository;
//import com.tss.MiniProject.FoodOrderingSystem.repository.OrderRepository;
//
//import java.util.List;
//
//public class DeliveryService {
//    private final DeliveryAgentRepository agentRepo;
//    private final OrderRepository orderRepo;
//
//    public DeliveryService(DeliveryAgentRepository agentRepo, OrderRepository orderRepo) {
//        this.agentRepo = agentRepo;
//        this.orderRepo = orderRepo;
//    }
//
//    // view pending orders (unassigned or assigned to this agent)
//    public List<Order> getPendingOrdersForAgent(String agentId) {
//        // pending: ASSIGNED to this agent but not DELIVERED; or PLACED waiting for assignment
//        return orderRepo.getAll().stream()
//                .filter(o -> ((agentId.equals(o.getDeliveryAgentId()) && !o.getStatus().equals("DELIVERED")))
//                .toList();
//    }
//
//    // agent accepts assigned order (mark OUT_FOR_DELIVERY)
//    public void acceptOrder(String agentId, int orderId) {
//        Order o = orderRepo.findById(orderId);
//        if (o == null) throw new IllegalArgumentException("Order not found");
//        if (!agentId.equals(o.getDeliveryAgentId())) throw new IllegalArgumentException("Order not assigned to you");
//        o.setStatus(OutForDeliveryStatus);
//        orderRepo.update(o);
//    }
//
//    public void markDelivered(String agentId, int orderId) {
//        Order o = orderRepo.findById(orderId);
//        if (o == null) throw new IllegalArgumentException("Order not found");
//        if (!agentId.equals(o.getDeliveryPartnerId())) throw new IllegalArgumentException("Order not assigned to you");
//        o.setStatus("DELIVERED");
//        orderRepo.update(o);
//
//        // mark agent free and pay earnings persisted in agent repository
//        DeliveryAgent a = agentRepo.findById(agentId);
//        if (a != null) {
//            a.setA(true);
//            agentRepo.update(a);
//        }
//    }
//
//    public List<Order> getDeliveredHistory(String agentId) {
//        return orderRepo.getAll().stream()
//                .filter(o -> agentId.equals(o.getDeliveryAgentId()) && o.getStatus().equals("DELIVERED"))
//                .toList();
//    }
//
//    // cancel order (agent or admin)
//    public void cancelOrder(String agentId, int orderId) {
//        Order o = orderRepo.findById(orderId);
//        if (o == null) throw new IllegalArgumentException("Order not found");
//        // depending on rule: agent can cancel if not delivered
//        if (o.getStatus().equalsIgnoreCase("DELIVERED")) throw new IllegalArgumentException("Cannot cancel delivered order");
//        o.setStatus("CANCELED");
//        orderRepo.update(o);
//        // make agent free if assigned
//        if (o.getDeliveryPartnerId() != null) {
//            DeliveryAgent a = agentRepo.findById(o.getDeliveryPartnerId());
//            if (a != null) { a.setFree(true); agentRepo.update(a); }
//        }
//    }
//}