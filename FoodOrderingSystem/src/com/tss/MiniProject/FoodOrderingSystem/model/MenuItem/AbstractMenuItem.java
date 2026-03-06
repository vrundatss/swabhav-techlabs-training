package com.tss.MiniProject.FoodOrderingSystem.model.MenuItem;

import com.tss.MiniProject.FoodOrderingSystem.model.enums.ItemCategoryType;

public class AbstractMenuItem implements MenuItem{
    private final String id;
    private final String name;
    private final Double price;
    private final ItemCategoryType category;
    private boolean isAvailable = true;
//    private Double finalPrice;

    public AbstractMenuItem(String id , String name, Double price, ItemCategoryType category ,Boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }

//    public Double getFinalPrice() {
//        return finalPrice;
//    }

    @Override
    public void display() {
        Double finalPrice = category.calculateFinalPrice(price);

        System.out.printf("| %-10s | %-18s | %-15s | %-9.2frs.| %-9.2frs.|%n",
                id,
                name.length() > 18 ? name.substring(0, 15) + "..." : name,
                category.name(),
//                stock,
                price,
                finalPrice);
    }

    public ItemCategoryType getCategory() {
        return category;
    }


    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public void setAvailable(boolean status) {
        isAvailable = status;
    }
}
