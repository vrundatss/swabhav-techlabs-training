package FoodOrderingSystem.model.MenuItem.decorator;

import com.tss.MiniProject.FoodOrderingSystem.model.Constants.Constant;
import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.MenuItem;
import com.tss.MiniProject.FoodOrderingSystem.model.enums.ItemCategoryType;

public class SpicyDecorator extends MenuItemDecorator {
    public SpicyDecorator(MenuItem item) {
        super(item);
    }

    @Override
    public String getName() {
        return item.getName() + " (Spicy)";
    }

    @Override
    public Double getPrice() {
        return item.getPrice() + Constant.EXTRA_SPICY_PRICE;
    }

    @Override
    public String getId() {
        return "";
    }
    @Override
    public ItemCategoryType getCategory() {
        return ItemCategoryType.FAST_FOOD;
    }

    @Override
    public void display() {
        System.out.printf("- %-25s : %.2f%n", getName(), getPrice());
    }
}
