package FoodOrderingSystem.model.discount;

import java.util.List;

public class CompositeDiscountStrategy implements DiscountStrategy {
    private final List<DiscountStrategy> strategies;

    public CompositeDiscountStrategy(List<DiscountStrategy> strategies) {
        this.strategies = strategies;
    }
    @Override
    public double applyDiscount(double totalAmount) {
        double totalDiscount = 0;
        for (DiscountStrategy s : strategies) {
            if (s.isApplicable()) {
                totalDiscount += s.applyDiscount(totalAmount);
            }
        }
        return totalDiscount;
    }
//    @Override
//    public double applyDiscount(double totalAmount) {
//        double totalDiscount = 0;
//        for (DiscountStrategy strategy : strategies) {
//            double discount = strategy.applyDiscount(totalAmount - totalDiscount);
//            totalDiscount += discount;
//        }
//        return totalDiscount;
//    }

    @Override
    public String getDescription() {
        return "Composite Discount (" + strategies.size() + " strategies)";
    }

    @Override
    public boolean isApplicable() {
        return false;
    }
}