package FoodOrderingSystem.model.discount;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DayBasedDiscountStrategy implements DiscountStrategy {

    @Override
    public double applyDiscount(double totalAmount) {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        switch (today) {
            case SUNDAY -> {
                System.out.println("Sunday Special: 30% OFF!");
                return totalAmount * 0.30;
            }
            case FRIDAY -> {
                System.out.println("Friday Offer: 20% OFF!");
                return totalAmount * 0.20;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public String getDescription() {
        return "Day-Based Discount (Friday & Sunday)";
    }

    @Override
    public boolean isApplicable() {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        return today == DayOfWeek.FRIDAY || today == DayOfWeek.SUNDAY;
    }
}