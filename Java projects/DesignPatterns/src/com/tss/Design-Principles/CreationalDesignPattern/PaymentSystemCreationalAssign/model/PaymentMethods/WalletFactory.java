package com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.PaymentMethods;

import com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.PaymentProcessor.IPaymentProcessor;
import com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.PaymentProcessor.WalletPaymentProcessor;

public class WalletFactory implements IPaymentMethodFactory{

    @Override
    public String getMethod() {
        return "WALLET";
    }

    @Override
    public IPaymentProcessor paymentProcessor() {
        return new WalletPaymentProcessor();
    }
}
