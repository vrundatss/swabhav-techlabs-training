package com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.PaymentProcessor;

public class CreditCardPaymentProcessor implements IPaymentProcessor{
    @Override
    public void pay(double amount) {
        System.out.println(amount + " rs. Paid via Credit Card");
    }
}
