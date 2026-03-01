package com.tss.MiniProject.FoodOrderingSystem.model.discount;

public interface DiscountStrategy {
    double applyDiscount(double totalAmount);
    String getDescription();
    boolean isApplicable(double totalAmount);
}
