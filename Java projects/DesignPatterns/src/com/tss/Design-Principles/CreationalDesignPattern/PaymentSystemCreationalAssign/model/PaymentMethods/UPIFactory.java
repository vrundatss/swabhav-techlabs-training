package com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.PaymentMethods;

import com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.PaymentProcessor.IPaymentProcessor;
import com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.PaymentProcessor.UPIPaymentProcessor;

public class UPIFactory implements IPaymentMethodFactory{

    @Override
    public String getMethod() {
            return "UPI";
    }

    @Override
    public IPaymentProcessor paymentProcessor() {
        return new UPIPaymentProcessor();
    }
}
