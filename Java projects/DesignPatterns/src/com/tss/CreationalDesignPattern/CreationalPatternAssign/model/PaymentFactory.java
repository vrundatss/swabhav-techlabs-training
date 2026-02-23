package com.tss.CreationalDesignPattern.CreationalPatternAssign.model;

public interface PaymentFactory {
    IPaymentProcessor processPayment(String type);
    String getDomain();
}
