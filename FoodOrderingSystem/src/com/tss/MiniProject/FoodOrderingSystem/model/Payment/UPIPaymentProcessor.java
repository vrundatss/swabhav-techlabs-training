package com.tss.MiniProject.FoodOrderingSystem.model.Payment;
import java.time.LocalDateTime;

public class UPIPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(Payment payment, double amount) {
        System.out.println("Processing UPI Payment of rs. " + amount);

        payment.setStatus(new CompletedStatus());
        payment.setPaidAt(LocalDateTime.now());
        System.out.println("UPI Payment Successful!");
        return true;
    }

}