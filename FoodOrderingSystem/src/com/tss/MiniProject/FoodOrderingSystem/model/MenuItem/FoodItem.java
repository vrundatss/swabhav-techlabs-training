package com.tss.MiniProject.FoodOrderingSystem.model.MenuItem;

import com.tss.MiniProject.FoodOrderingSystem.model.enums.ItemCategoryType;

public class FoodItem extends AbstractMenuItem {

    // Private constructor accepting the Builder
    private FoodItem(Builder b) {
        // Fixed the syntax error: b.isAvailable was missing
        super(b.id, b.name, b.price, b.category , b.isAvailable);
    }

    public static class Builder {
        private String id;
        private String name;
        private double price;
//        private int stock;
        private ItemCategoryType category;
        private boolean isAvailable;

        public Builder id(String id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder price(double price) { this.price = price; return this; }
//        public Builder stock(int stock) { this.stock = stock; return this; }
        public Builder category(ItemCategoryType category) { this.category = category; return this; }
        public Builder isAvailable(boolean isAvailable) { this.isAvailable = isAvailable; return this; }

        public FoodItem build() {
            return new FoodItem(this);
        }
    }
}