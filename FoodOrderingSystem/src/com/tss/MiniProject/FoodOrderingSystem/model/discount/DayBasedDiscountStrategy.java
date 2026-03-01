package com.tss.MiniProject.FoodOrderingSystem.model.discount;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DayBasedDiscountStrategy implements DiscountStrategy {

    private static String description = "";
    @Override
    public double applyDiscount(double totalAmount) {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        switch (today) {
            case SUNDAY -> {
                description = "Sunday Special: 30% OFF!";
                return totalAmount * 0.30;
            }
            case FRIDAY -> {
                description = "Friday Offer: 20% OFF!";
                return totalAmount * 0.20;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public String getDescription() {
        return "Day-Based Discount " + description;
    }

    @Override
    public boolean isApplicable(double totalAmount) {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        return today == DayOfWeek.FRIDAY || today == DayOfWeek.SUNDAY;
    }
}