package com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.Domain;

import com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.PaymentMethods.IPaymentMethodFactory;

import java.util.List;

public interface IDomainFactory {
    String getDomainName();
    List<IPaymentMethodFactory> getSupportedPaymentFactories();
}
