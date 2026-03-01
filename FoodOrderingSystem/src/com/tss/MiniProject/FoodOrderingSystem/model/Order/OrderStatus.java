package com.tss.MiniProject.FoodOrderingSystem.model.Order;


public interface OrderStatus {
    void next(Order order);
    void cancel(Order order);
    String getName();
}