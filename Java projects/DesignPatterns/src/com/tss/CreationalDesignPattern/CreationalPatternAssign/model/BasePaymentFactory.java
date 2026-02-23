package com.tss.CreationalDesignPattern.CreationalPatternAssign.model;


public abstract class BasePaymentFactory implements PaymentFactory {

    @Override
    public IPaymentProcessor processPayment(String type) {
        return switch (type.toUpperCase()) {
            case "CREDITCARD" -> new CreditCardPayment();
            case "UPI" -> new UPIPayment();
            case "WALLET" -> new WalletPayment();
            default -> throw new IllegalArgumentException("Invalid payment type: " + type);
        };
    }
}