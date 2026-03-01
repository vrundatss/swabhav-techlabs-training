package com.tss.MiniProject.FoodOrderingSystem.factory;

import com.tss.MiniProject.FoodOrderingSystem.model.Payment.PaymentProcessor;

public interface PaymentFactory {
    PaymentProcessor createPaymentProcessor();
}
