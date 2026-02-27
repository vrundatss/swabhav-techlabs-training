package FoodOrderingSystem.data;

import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.FoodItem;
import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.MenuItem;
import com.tss.MiniProject.FoodOrderingSystem.model.enums.ItemCategoryType;

import java.util.List;
import java.util.UUID;

public class MenuInitializer {

    public static void loadDefaultMenu() {
        final DataStore db = DataStore.getInstance();
        List<MenuItem> menuItems = db.getMenuItems();

        // Prevent duplicates if menu already loaded
        if (!menuItems.isEmpty()) return;

        // Helper function to simplify building items
        java.util.function.BiFunction<String, ItemCategoryType, FoodItem> item = (name, category) ->
                new FoodItem.Builder()
                        .id("ITEM-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase())
                        .name(name)
                        .price(choosePrice(category))
                        .stock(10)
                        .category(category)
                        .isAvailable(true)
                        .build();

        // ---------- CHINESE ----------
        menuItems.add(item.apply("Veg Hakka Noodles", ItemCategoryType.CHINESE));
        menuItems.add(item.apply("Manchurian Combo", ItemCategoryType.CHINESE));

        // ---------- PUNJABI ----------
        menuItems.add(item.apply("Paneer Butter Masala", ItemCategoryType.PUNJABI));
        menuItems.add(item.apply("Dal Makhani", ItemCategoryType.PUNJABI));

        // ---------- SOUTH INDIAN ----------
        menuItems.add(item.apply("Masala Dosa", ItemCategoryType.SOUTH_INDIAN));
        menuItems.add(item.apply("Idli Sambar", ItemCategoryType.SOUTH_INDIAN));

        // ---------- GUJARATI ----------
        menuItems.add(item.apply("Gujarati Thali", ItemCategoryType.GUJARATI));
        menuItems.add(item.apply("Dhokla", ItemCategoryType.GUJARATI));

        // ---------- ITALIAN ----------
        menuItems.add(item.apply("Pasta Alfredo", ItemCategoryType.ITALIAN));
        menuItems.add(item.apply("Garlic Bread", ItemCategoryType.ITALIAN));

        // ---------- MEXICAN ----------
        menuItems.add(item.apply("Tacos", ItemCategoryType.MEXICAN));
        menuItems.add(item.apply("Nachos", ItemCategoryType.MEXICAN));

        // ---------- FAST FOOD ----------
        menuItems.add(item.apply("Veg Burger", ItemCategoryType.FAST_FOOD));
        menuItems.add(item.apply("French Fries", ItemCategoryType.FAST_FOOD));

        // ---------- DESSERT ----------
        menuItems.add(item.apply("Chocolate Lava Cake", ItemCategoryType.DESSERT));
        menuItems.add(item.apply("Brownie with Ice Cream", ItemCategoryType.DESSERT));

        // ---------- BEVERAGE ----------
        menuItems.add(item.apply("Cold Coffee", ItemCategoryType.BEVERAGE));
        menuItems.add(item.apply("Soft Drink", ItemCategoryType.BEVERAGE));

        // ---------- CATEGORY-WISE COMBOS ----------
        menuItems.add(item.apply("Chinese Combo (Noodles + Manchurian + Drink)", ItemCategoryType.COMBO));
        menuItems.add(item.apply("Punjabi Combo (Paneer Butter Masala + Naan + Lassi)", ItemCategoryType.COMBO));
        menuItems.add(item.apply("South Indian Combo (Dosa + Idli + Coffee)", ItemCategoryType.COMBO));
        menuItems.add(item.apply("Gujarati Combo (Thali + Farsan + Jalebi)", ItemCategoryType.COMBO));
        menuItems.add(item.apply("Italian Combo (Pizza + Garlic Bread + Drink)", ItemCategoryType.COMBO));
        menuItems.add(item.apply("Fast Food Combo (Burger + Fries + Coke)", ItemCategoryType.COMBO));
        menuItems.add(item.apply("Dessert Combo (Brownie + Ice Cream + Coffee)", ItemCategoryType.COMBO));
        menuItems.add(item.apply("Beverage Combo (Cold Coffee + Mojito)", ItemCategoryType.COMBO));
        menuItems.add(item.apply("Family Feast Combo (Any 3 Combos + Dessert)", ItemCategoryType.COMBO));

        System.out.println("Default Menu Loaded: " + menuItems.size() + " items.");
    }

    private static double choosePrice(ItemCategoryType cat) {
        // Optional: auto-assign base prices per category
        return switch (cat) {
            case CHINESE -> 220.0;
            case PUNJABI -> 260.0;
            case SOUTH_INDIAN -> 180.0;
            case GUJARATI -> 200.0;
            case ITALIAN -> 300.0;
            case MEXICAN -> 250.0;
            case FAST_FOOD -> 150.0;
            case DESSERT -> 180.0;
            case BEVERAGE -> 120.0;
            case COMBO -> 450.0;
        };
    }
}