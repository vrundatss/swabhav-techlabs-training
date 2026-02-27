package FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
import com.tss.MiniProject.FoodOrderingSystem.model.Constants.Constant;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.DeliveredStatus;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.Order;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalyticsService {

    private final DataStore db = DataStore.getInstance();

    /**
     * Group orders by their Status Name (using the State Pattern's getName() method).
     */
    public Map<String, Long> ordersByStatus() {
        return db.getOrders().stream()
                .collect(Collectors.groupingBy(
                        o -> o.getStatus().getName(), // Get string name from State object
                        Collectors.counting()
                ));
    }

    /**
     * Total Sales: Sum of all completed (Delivered) orders.
     */
    public double totalSalesValue() {
        return db.getOrders().stream()
                .filter(o -> o.getStatus() instanceof DeliveredStatus)
                .mapToDouble(Order::getFinalAmount)
                .sum();
    }

    // platform revenue is same as admin revenue
    public double getPlatformRevenue() {
        return totalSalesValue() * Constant.ADMIN_COMMISSION;
    }


    public double getTotalAgentPayouts() {
        return totalSalesValue() * Constant.PARTNER_SHARE;
    }

    public double avgOrderValue() {
        List<Order> all = db.getOrders();
        return all.stream()
                .mapToDouble(Order::getFinalAmount)
                .average()
                .orElse(0.0);
    }

    /**
     * Reports & Analytics: Identify most popular menu items from central order list.
     */
    public Map<String, Integer> mostOrderedItems() {
        return db.getOrders().stream()
                .flatMap(o -> o.getItemIdQty().entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.summingInt(Map.Entry::getValue)
                ));
    }
}


//package com.tss.MiniProject.FoodOrderingSystem.service;
//
//import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
//import com.tss.MiniProject.FoodOrderingSystem.repository.OrderRepository;
//
//import java.util.Map;
//import java.util.stream.Collectors;
//
//public class AnalyticsService {
//    private final OrderRepository orderRepo;
//
//    private final DataStore db  = DataStore.getInstance();
//
//    public AnalyticsService(OrderRepository orderRepo) {
//        this.orderRepo = orderRepo;
//    }
//
//    public Map<String, Long> ordersByStatus() {
//        return orderRepo.getAll().stream().collect(Collectors.groupingBy(o -> o.getStatus(), Collectors.counting()));
//    }
//
//    public double totalRevenue() {
//        return orderRepo.getAll().stream().mapToDouble(o -> o.getFinalAmount()).sum();
//    }
//
//    public double avgOrderValue() {
//        var all = orderRepo.getAll();
//        return all.stream().mapToDouble(o -> o.getFinalAmount()).average().orElse(0.0);
//    }
//}