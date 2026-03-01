package com.tss.MiniProject.FoodOrderingSystem.model.enums;

import java.util.*;

public class DynamicCategoryRegistry {

    private static final Map<String, DynamicCategory> dynamicCategories = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    private DynamicCategoryRegistry() {}

    public static void addCategory(String name, double taxRate, double discountRate) {
        dynamicCategories.put(name.toUpperCase(), new DynamicCategory(name.toUpperCase(), taxRate, discountRate));
    }

    // check that category exists or not
    public static boolean exists(String name) {
        return dynamicCategories.containsKey(name.toUpperCase());
    }

    // get all dynamic categories
    public static Collection<DynamicCategory> getAllDynamicCategories() {
        return dynamicCategories.values();
    }

    // get all categories (enum + dynamic)
    public static List<String> getAllCategoryNames() {
        List<String> all = new ArrayList<>();
        Arrays.stream(ItemCategoryType.values()).forEach(e -> all.add(e.name()));
        all.addAll(dynamicCategories.keySet());
        return all;
    }


    public static class DynamicCategory {
        private final String name;
        private final double taxRate;
        private final double discountRate;

        public DynamicCategory(String name, double taxRate, double discountRate) {
            this.name = name;
            this.taxRate = taxRate;
            this.discountRate = discountRate;
        }

        public String getName() { return name; }
        public double getTaxRate() { return taxRate; }
        public double getDiscountRate() { return discountRate; }

        @Override
        public String toString() {
            return String.format("%-15s | %-10.2f | %-10.2f", name, taxRate, discountRate);
        }
    }
}