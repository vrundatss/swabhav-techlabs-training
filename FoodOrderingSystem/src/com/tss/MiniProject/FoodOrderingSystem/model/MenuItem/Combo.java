package com.tss.MiniProject.FoodOrderingSystem.model.MenuItem;

import com.tss.MiniProject.FoodOrderingSystem.model.enums.ItemCategoryType;

import java.util.ArrayList;
import java.util.List;

public class Combo extends AbstractMenuItem {

    private final List<MenuItem> items = new ArrayList<>();
    private final List<String> includedItemIds;


    private Combo(Builder b) {
        super(b.id, b.name, b.price, ItemCategoryType.COMBO ,b.available);
        this.includedItemIds = b.includedItemIds != null ? b.includedItemIds : new ArrayList<>();
    }

    public List<String> getIncludedItemIds() {
        return includedItemIds;
    }

    public static class Builder {
        private String id;
        private String name;
        private double price;
//        private int stock;
        private boolean available = true;
        private List<String> includedItemIds;

        public Builder id(String id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder price(double price) { this.price = price; return this; }
//        public Builder stock(int stock) { this.stock = stock; return this; }
        public Builder available(boolean available) { this.available = available; return this; }
        public Builder includedItemIds(List<String> ids) { this.includedItemIds = ids; return this; }

        public Combo build() { return new Combo(this); }
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    public List<MenuItem> getItems() {
        return items;
    }

     @Override
     public Double getPrice() {
         return items.stream().mapToDouble(MenuItem::getPrice).sum();
     }

    @Override
    public void display() {
        System.out.println("\nCombo: " + getName());
        for (MenuItem item : items) {
            item.display();
        }
        System.out.printf("Combo Price: %.2f%n", getPrice() + " rs.");
    }

//    @Override
//    public Integer getStock() {
//        // Stock = lowest available stock among included items
//        return items.stream()
//                .filter(i -> i instanceof Inventory)
//                .mapToInt(i -> ((Inventory) i).getStock())
//                .min()
//                .orElse(0);
//    }
//
//    @Override
//    public void reduceStock(Integer quantity) {
//        for (MenuItem item : items) {
//            if (item instanceof Inventory inventory) {
//                inventory.reduceStock(quantity);
//            }
//        }
//    }
//
//    @Override
//    public void addStock(Integer quantity) {
//        for (MenuItem item : items) {
//            if (item instanceof Inventory inventory) {
//                inventory.addStock(quantity);
//            }
//        }
//    }

//    @Override
//    public boolean isAvailable() {
//        return getStock() > 0 && super.isAvailable();
//    }
}