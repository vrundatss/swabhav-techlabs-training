package com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.Domain;

import com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.PaymentMethods.CreditCardFactory;
import com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.PaymentMethods.IPaymentMethodFactory;
import com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.PaymentMethods.UPIFactory;
import com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.PaymentMethods.WalletFactory;

import java.util.Arrays;
import java.util.List;

public class ECommerceFactory implements IDomainFactory{

    private final List<IPaymentMethodFactory> paymentFactories = Arrays.asList(
            new UPIFactory(),
            new CreditCardFactory(),
            new WalletFactory()
    );

    @Override
    public String getDomainName() {
        return "ECommerce";
    }

    @Override
    public List<IPaymentMethodFactory> getSupportedPaymentFactories() {
        return paymentFactories;
    }
}
