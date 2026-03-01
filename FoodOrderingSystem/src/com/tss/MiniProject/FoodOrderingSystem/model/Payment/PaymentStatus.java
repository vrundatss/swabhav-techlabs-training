package com.tss.MiniProject.FoodOrderingSystem.model.Payment;

public interface PaymentStatus {
    void next(Payment payment);
    void fail(Payment payment);
    String getName();
}