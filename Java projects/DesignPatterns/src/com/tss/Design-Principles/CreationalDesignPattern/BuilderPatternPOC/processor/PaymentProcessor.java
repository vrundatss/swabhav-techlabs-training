package com.tss.CreationalDesignPattern.BuilderPatternPOC.processor;

import com.tss.CreationalDesignPattern.BuilderPatternPOC.model.Payment;

public class PaymentProcessor {
    private final Payment payment;

    public PaymentProcessor(Payment payment) {
        this.payment = payment;
    }

    public void makePayment(){
        payment.processPayment();
        System.out.println("-----------------------------------------------");
    }
}
