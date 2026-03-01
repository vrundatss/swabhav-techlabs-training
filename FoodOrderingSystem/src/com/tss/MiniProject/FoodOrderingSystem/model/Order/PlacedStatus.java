package com.tss.MiniProject.FoodOrderingSystem.model.Order;

public class PlacedStatus implements OrderStatus {
    @Override
    public void next(Order order) {
        order.setStatus(new PendingStatus());
    }

    @Override
    public void cancel(Order order) {
        order.setStatus(new CancelledStatus());
    }

    @Override
    public String getName() {
        return "PLACED";
    }
}