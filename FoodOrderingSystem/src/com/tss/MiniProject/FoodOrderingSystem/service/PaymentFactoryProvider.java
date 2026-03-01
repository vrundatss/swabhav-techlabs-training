package com.tss.MiniProject.FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.factory.CODFactory;
import com.tss.MiniProject.FoodOrderingSystem.factory.CreditCardFactory;
import com.tss.MiniProject.FoodOrderingSystem.factory.PaymentFactory;
import com.tss.MiniProject.FoodOrderingSystem.factory.UPIFactory;

import java.util.HashMap;
import java.util.Map;

public class PaymentFactoryProvider {

    private static final Map<String, PaymentFactory> registry = new HashMap<>();

    static {
        register("CREDIT_CARD", new CreditCardFactory());
        register("UPI", new UPIFactory());
        register("COD", new CODFactory());
    }

    public static void register(String method, PaymentFactory factory) {
        registry.put(method.toUpperCase(), factory);
    }

    public static PaymentFactory getFactory(String method) {
        PaymentFactory factory = registry.get(method.toUpperCase());
        if (factory == null) {
            throw new IllegalArgumentException("Unsupported payment method: " + method);
        }
        return factory;
    }

}