package com.tss.CreationalDesignPattern.CreationalPatternAssign.model;

public class UPIPayment implements IPaymentProcessor{
    @Override
    public void Pay(double amount) {
        System.out.println(amount + " rs. Pay using UPI...");
    }
}
