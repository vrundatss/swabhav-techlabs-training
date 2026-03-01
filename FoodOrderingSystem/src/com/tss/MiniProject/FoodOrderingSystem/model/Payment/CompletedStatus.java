package com.tss.MiniProject.FoodOrderingSystem.model.Payment;

public class CompletedStatus implements PaymentStatus {

    @Override
    public void next(Payment payment) {
        System.out.println("Payment already completed. No further transition allowed.");
    }

    @Override
    public void fail(Payment payment) {
        System.out.println("Cannot fail a completed payment.");
    }

    @Override
    public String getName() {
        return "COMPLETED";
    }
}
