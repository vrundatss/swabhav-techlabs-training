package com.tss.MiniProject.FoodOrderingSystem.model.discount;

public class FlatBillDiscountStrategy implements DiscountStrategy {

    @Override
    public double applyDiscount(double totalAmount) {
        if (totalAmount > 1000) {
            System.out.println("Flat 250 rs. off for orders above 1000 rs.");
            return 250;
        } else if (totalAmount > 500) {
            System.out.println("Flat 100 rs. off for orders above 500 rs.");
            return 100;
        }
        return 0;
    }

    @Override
    public String getDescription() {
        return "Flat Discount (₹250 off above ₹1000 | ₹100 off above ₹500)";
    }
    @Override
    public boolean isApplicable(double totalAmount) {
        return totalAmount >= 500;
    }
}