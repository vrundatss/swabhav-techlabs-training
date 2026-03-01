package com.tss.MiniProject.FoodOrderingSystem.model.discount;

import java.time.LocalDate;
import java.time.Month;

public class FestivalDiscountStrategy implements DiscountStrategy {

    private String description = "";
    @Override
    public double applyDiscount(double totalAmount) {
        LocalDate today = LocalDate.now();

        if (isDiwali(today)) {
            return totalAmount * 0.40;
        } else if (isHoli(today)) {
            return totalAmount * 0.35;
        } else if (isNavratri(today)) {
            return totalAmount * 0.25;
        }
        return 0;
    }

    private boolean isDiwali(LocalDate date) {
        description = "Diwali Offer: 40% OFF!";

        return date.getMonth() == Month.NOVEMBER && date.getDayOfMonth() == 12;
    }

    private boolean isHoli(LocalDate date) {
        description = "Holi Offer: 35% OFF!";

        return date.getMonth() == Month.MARCH && ( date.getDayOfMonth() == 1  || date.getDayOfMonth() == 2  || date.getDayOfMonth() == 3 || date.getDayOfMonth() == 4 );
    }

    private boolean isNavratri(LocalDate date) {
        description = "Navratri Offer: 25% OFF!";

        return date.getMonth() == Month.OCTOBER && (date.getDayOfMonth() >= 5 && date.getDayOfMonth() <= 13);
    }

    @Override
    public String getDescription() {
        return "Festival Discount ==>  "  + description;
    }

    @Override
    public boolean isApplicable(double totalAmount) {
        LocalDate today = LocalDate.now();
        return isDiwali(today) || isHoli(today) || isNavratri(today);
    }
}