package FoodOrderingSystem.service;


import com.tss.MiniProject.FoodOrderingSystem.model.discount.*;

import java.util.List;

public class DiscountService {
    private final DiscountStrategy discountStrategy;

    public DiscountService() {
        this.discountStrategy = new CompositeDiscountStrategy(List.of(
                new FlatBillDiscountStrategy(),
                new DayBasedDiscountStrategy(),
                new FestivalDiscountStrategy()
        ));
    }

    public double calculateDiscount(double totalAmount) {
        return discountStrategy.applyDiscount(totalAmount);
    }

    public String getDescription() {
        return discountStrategy.getDescription();
    }
}