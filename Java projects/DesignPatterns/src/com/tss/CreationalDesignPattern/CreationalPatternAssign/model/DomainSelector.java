package com.tss.CreationalDesignPattern.CreationalPatternAssign.model;

public class DomainSelector {
    public static PaymentFactory getFactory(String domain) {
        return switch (domain.toUpperCase()) {
            case "ECOMMERCE" -> new ECommFactory();
            case "GAMING" -> new GamingFactory();
            default -> throw new IllegalArgumentException("Unknown domain: " + domain);
        };
    }
}
