package FoodOrderingSystem.model.MenuItem;

import com.tss.MiniProject.FoodOrderingSystem.model.enums.ItemCategoryType;

public interface MenuItem {
    String getId();
    String getName();
    Double getPrice();
    ItemCategoryType getCategory();
    void display();
    void addStock(Integer quantity);
    Integer getStock();
    void reduceStock(Integer quantity);
    boolean isAvailable();
}
