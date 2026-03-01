package com.tss.MiniProject.FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.controller.AppFacade;
import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.Order;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.PendingStatus;
import com.tss.MiniProject.FoodOrderingSystem.model.Payment.Payment;
import com.tss.MiniProject.FoodOrderingSystem.model.Payment.PaymentProcessor;
import com.tss.MiniProject.FoodOrderingSystem.util.ValidationUtil;
//import com.tss.MiniProject.com.tss.MiniProject.FoodOrderingSystem.model.Payment.PaymentFactoryProvider;
import java.util.Scanner;


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

    public boolean handlePayment(Scanner  sc ,Order order) {
        System.out.println("\n" + "\u001B[36m" + "╔══════════════════════════════════════════════╗");
        System.out.println("║                PAYMENT GATEWAY               ║");
        System.out.println("╚══════════════════════════════════════════════╝" + "\u001B[0m");

        System.out.printf(" Payable Amount: \u001B[32m₹%.2f\u001B[0m %n", order.getFinalAmount());
        System.out.println("\nSelect Payment Method:");
        System.out.println("1. UPI");
        System.out.println("2. Credit/Debit Card");
        System.out.println("3. Cash on Delivery");
        System.out.println("0. Cancel Payment");

        int choice = ValidationUtil.getValidInt(sc, "Method", 0);
        String method = "";

        if (choice == 0) {
            System.out.println("\u001B[31mPayment Cancelled.\u001B[0m");
            return false;
        }

        switch (choice) {
            case 1 -> {
                method = "UPI";
                order.setPaymentMethod("UPI");
                if(!ValidationUtil.getValidUPI(sc)){
                    System.out.println("UPI failed...");
                    return false;
                } // simulation of upi
            }
            case 2 -> {
                method = "CARD";
                order.setPaymentMethod("CARD");
                if (!processCardDetails(sc)) return false; // Exit if card details fail
            }
            case 3 -> {
                method = "CASH";
                order.setPaymentMethod("CASH ON DELIVERY");
            }
            default -> {
                System.out.println("\u001B[31mPayment Cancelled.\u001B[0m");
                return false;
            }
        }
            sc.nextLine();
            System.out.print("\nConfirm payment of ₹" + order.getFinalAmount() + " via " + method + "? (yes/no): ");
            if (ValidationUtil.getValidBoolean(sc)) {
                this.processPayment(order.getId(), method, order.getFinalAmount());
                return true;
            }
        return false; // not confirm , aborted...
    }

    private boolean processCardDetails(Scanner sc) {
        System.out.println("\n--- ENTER CARD DETAILS ---");
        sc.nextLine();
        System.out.print("Card Holder Name: ");
        String holder = sc.nextLine();

        if (holder.length() < 3) {
            System.out.println("\u001B[31mInvalid Holder Name!\u001B[0m");
            return false;
        }

        String cardNumber = ValidationUtil.readHiddenInput(sc, "Enter 16-digit Card Number: ");
        // for validation of 16 digits only
        if (!cardNumber.matches("\\d{16}")) {
            System.out.println("\u001B[31mInvalid Card Number! Must be 16 digits.\u001B[0m");
            return false;
        }

        String cvv = ValidationUtil.readHiddenInput(sc, "Enter 3-digit CVV: ");
        //  3 digits only
        if (!cvv.matches("\\d{3}")) {
            System.out.println("\u001B[31mInvalid CVV! Must be 3 digits.\u001B[0m");
            return false;
        }

        System.out.println("\u001B[32mCard Verified Successfully.\u001B[0m");
        return true;
    }

    public Payment findPaymentByOrderId(int orderId) {
        return db.getPayments().stream()
                .filter(p -> p.getOrderId() == orderId)
                .findFirst()
                .orElse(null);
    }
}