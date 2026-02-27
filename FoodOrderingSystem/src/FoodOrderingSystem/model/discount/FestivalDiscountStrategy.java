package FoodOrderingSystem.model.discount;

import java.time.LocalDate;
import java.time.Month;

public class FestivalDiscountStrategy implements DiscountStrategy {

    @Override
    public double applyDiscount(double totalAmount) {
        LocalDate today = LocalDate.now();

        if (isDiwali(today)) {
            System.out.println("Diwali Offer: 40% OFF!");
            return totalAmount * 0.40;
        } else if (isHoli(today)) {
            System.out.println("Holi Offer: 35% OFF!");
            return totalAmount * 0.35;
        } else if (isNavratri(today)) {
            System.out.println("Navratri Offer: 25% OFF!");
            return totalAmount * 0.25;
        }
        return 0;
    }

    private boolean isDiwali(LocalDate date) {
        return date.getMonth() == Month.NOVEMBER && date.getDayOfMonth() == 12;
    }

    private boolean isHoli(LocalDate date) {
        return date.getMonth() == Month.MARCH && date.getDayOfMonth() == 6;
    }

    private boolean isNavratri(LocalDate date) {
        return date.getMonth() == Month.OCTOBER && (date.getDayOfMonth() >= 5 && date.getDayOfMonth() <= 13);
    }

    @Override
    public String getDescription() {
        return "Festival Discount (Diwali, Holi, Navratri)";
    }

    @Override
    public boolean isApplicable() {
        LocalDate today = LocalDate.now();
        return isDiwali(today) || isHoli(today) || isNavratri(today);
    }
}