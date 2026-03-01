package com.tss.MiniProject.FoodOrderingSystem.model.MenuItem;

import com.tss.MiniProject.FoodOrderingSystem.model.enums.ItemCategoryType;

public class AbstractMenuItem implements MenuItem{
    private final String id;
    private final String name;
    private final Double price;
//    private Integer stock;
    private final ItemCategoryType category;
    private boolean isAvailable = true;

    public AbstractMenuItem(String id , String name, Double price, ItemCategoryType category ,Boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
//        this.stock = stock;
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

    @Override
    public void display() {
        double finalPrice = category.calculateFinalPrice(price);

        System.out.printf("| %-10s | %-18s | %-15s | %-9.2frs.| %-9.2frs.|%n",
                id,
                name.length() > 18 ? name.substring(0, 15) + "..." : name,
                category.name(),
//                stock,
                price,
                finalPrice);
    }
//    @Override
//    public Integer getStock() {
//        return this.stock;
//    }
//
//    @Override
//    public void reduceStock(Integer quantity) {
//        if(quantity <= stock)
//            stock -= quantity;
//
//        throw new IllegalStateException(name + " is out of stock!!!...");
//    }

    public ItemCategoryType getCategory() {
        return category;
    }

//    @Override
//    public void addStock(Integer quantity) {
//        stock += quantity;
//    }
//
    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public void setAvailable(boolean status) {
        isAvailable = status;
    }
}
