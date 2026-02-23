package com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.PaymentMethods;

import com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.PaymentProcessor.IPaymentProcessor;

public interface IPaymentMethodFactory {
    String getMethod();
    IPaymentProcessor paymentProcessor();
}
