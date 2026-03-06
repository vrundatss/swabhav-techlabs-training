package com.tss.MiniProject.FoodOrderingSystem.model.MenuItem;

import com.tss.MiniProject.FoodOrderingSystem.model.enums.DynamicCategoryRegistry;
import com.tss.MiniProject.FoodOrderingSystem.model.enums.ItemCategoryType;

public interface MenuItem {
    String getId();
    String getName();
    Double getPrice();
    ItemCategoryType getCategory();
    void display();

    boolean isAvailable();
    default void setAvailable(boolean status){
        status = true;
    };

//    DynamicCategoryRegistry getDynamicCategory();
}
