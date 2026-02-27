package FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.PendingStatus;
import com.tss.MiniProject.FoodOrderingSystem.model.Payment.Payment;
import com.tss.MiniProject.FoodOrderingSystem.model.Payment.PaymentProcessor;
//import com.tss.MiniProject.FoodOrderingSystem.model.Payment.PaymentFactoryProvider;

public class PaymentService {

    private final DataStore db = DataStore.getInstance();

    public Payment processPayment(int orderId, String method, double amount) {
        Payment p = new Payment(orderId, method);

        try {
            var factory = PaymentFactoryProvider.getFactory(method);
            PaymentProcessor processor = factory.createPaymentProcessor();
            boolean ok = processor.processPayment(p, amount);

            if (ok) {
                // Find the order in the central list to update its state
                db.getOrders().stream()
                        .filter(o -> o.getId() == orderId)
                        .findFirst()
                        .ifPresent(order -> {
                            // Using your State Pattern: Transition to Pending (waiting for agent)
                            order.setStatus(new PendingStatus());
                            System.out.println("Payment Successful. Order " + orderId + " is now PENDING.");
                        });
            } else {
                p.markFailed();
                // set a CancelledStatus or FailedPaymentStatus here
            }

        } catch (IllegalArgumentException ex) {
            p.markFailed();
        }

        db.getPayments().add(p);
        return p;
    }

    public Payment findPaymentByOrderId(int orderId) {
        return db.getPayments().stream()
                .filter(p -> p.getOrderId() == orderId)
                .findFirst()
                .orElse(null);
    }
}



//package com.tss.MiniProject.FoodOrderingSystem.service;
//
//import com.tss.MiniProject.FoodOrderingSystem.factory.PaymentFactory;
//import com.tss.MiniProject.FoodOrderingSystem.model.Payment.Payment;
//import com.tss.MiniProject.FoodOrderingSystem.model.Payment.PaymentProcessor;
//import com.tss.MiniProject.FoodOrderingSystem.model.Payment.PendingStatus;
//
//public class PaymentService {
//
//    public boolean makePayment(int orderId, String method, double amount) {
//        Payment payment = new Payment(orderId, method, new PendingStatus(), null);
//
//        PaymentFactory factory = PaymentFactoryProvider.getFactory(method);
//        PaymentProcessor processor = factory.createPaymentProcessor();
//
//        boolean success = processor.processPayment(payment, amount);
//
//        System.out.println("Payment via " + method + " ==> " + payment.getStatus().getName());
//        return success;
//    }
//}