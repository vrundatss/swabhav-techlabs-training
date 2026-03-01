package com.tss.MiniProject.FoodOrderingSystem.model.Order;

public class DeliveredStatus implements OrderStatus {

    @Override
    public void next(Order order) {
        System.out.println("Order is delivered...");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Cannot cancel the order , it is already delivered");
    }

    @Override
    public String getName() {
        return "DELIVERED";
    }
}
