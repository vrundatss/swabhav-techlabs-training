package com.tss.MiniProject.FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
import com.tss.MiniProject.FoodOrderingSystem.model.Constants.Constant;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.DeliveredStatus;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.Order;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalyticsService {

    private final DataStore db = DataStore.getInstance();

    public List<Map.Entry<String, Long>> getMostFrequentItems(int topN) {
        return db.getOrders().stream()
                .flatMap(order -> order.getItemIdQty().entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingLong(Map.Entry::getValue)))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(topN)
                .collect(Collectors.toList());
    }

    public List<Map.Entry<String, Long>> getMostFrequentCustomers(int topN) {
        return db.getOrders().stream()
                .collect(Collectors.groupingBy(Order::getCustomerId, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(topN)
                .collect(Collectors.toList());
    }

}

