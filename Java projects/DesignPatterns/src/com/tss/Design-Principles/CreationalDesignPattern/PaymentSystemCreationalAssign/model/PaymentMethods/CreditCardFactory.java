package com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.PaymentMethods;

import com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.PaymentProcessor.CreditCardPaymentProcessor;
import com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.PaymentProcessor.IPaymentProcessor;

public class CreditCardFactory implements IPaymentMethodFactory{

    @Override
    public String getMethod() {
        return "CREDITCARD";
    }

    @Override
    public IPaymentProcessor paymentProcessor() {
        return new CreditCardPaymentProcessor();
    }
}
