package FoodOrderingSystem.model.enums;

public enum ItemCategoryType {
    CHINESE(0.05, 0.10),
    PUNJABI(0.05, 0.05),
    SOUTH_INDIAN(0.05, 0.08),
    GUJARATI(0.04, 0.07),
    ITALIAN(0.10, 0.05),
    MEXICAN(0.08, 0.10),
    FAST_FOOD(0.12, 0.15),
    DESSERT(0.06, 0.10),
    COMBO(0.07, 0.20),
    BEVERAGE(0.05 , 0.10);

    private final double taxRate;
    private final double categoryDiscount; // fraction

    ItemCategoryType(double taxRate, double categoryDiscount) {
        this.taxRate = taxRate;
        this.categoryDiscount = categoryDiscount;
    }

    public double getTaxRate() { return taxRate; }
    public double getCategoryDiscount() { return categoryDiscount; }

    public double calculateFinalPrice(double basePrice) {
        double afterCatDiscount = basePrice - (basePrice * categoryDiscount);
        return afterCatDiscount + (afterCatDiscount * taxRate);
    }
}