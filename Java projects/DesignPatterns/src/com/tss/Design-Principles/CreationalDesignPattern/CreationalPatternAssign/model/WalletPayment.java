package com.tss.CreationalDesignPattern.CreationalPatternAssign.model;

public class WalletPayment implements IPaymentProcessor{
    @Override
    public void Pay(double amount) {
        System.out.println(amount + " rs. Pay using Wallet...");
    }
}
