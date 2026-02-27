package FoodOrderingSystem.model.discount;

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
        return "Flat Bill Discount (100/250 rs. off)";
    }

    @Override
    public boolean isApplicable() {
        return true;
    }
}