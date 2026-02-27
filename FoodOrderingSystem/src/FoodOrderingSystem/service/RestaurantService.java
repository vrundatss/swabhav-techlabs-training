package FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.MenuItem;
import com.tss.MiniProject.FoodOrderingSystem.model.Restaurant.Restaurant;
import com.tss.MiniProject.FoodOrderingSystem.model.enums.ItemCategoryType;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantService {
    private final DataStore db = DataStore.getInstance();

    public void addRestaurant(Restaurant restaurant) {
        db.getRestaurants().add(restaurant);
        System.out.println("Added restaurant: " + restaurant.getName());
    }

    public void removeRestaurant(String restaurantId) {
        db.getRestaurants().removeIf(r -> r.getId().equals(restaurantId));
        System.out.println("Removed restaurant: " + restaurantId);
    }

    public Restaurant getRestaurantById(String id) {
        return db.getRestaurants().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<MenuItem> getItemsByCategory(String restaurantId, ItemCategoryType category) {
        Restaurant rest = getRestaurantById(restaurantId);
        if (rest == null) return Collections.emptyList();
        return rest.getMenuItems().stream()
                .filter(item -> item.getCategory() == category)
                .collect(Collectors.toList());
    }

    public void displayAllRestaurants() {
        System.out.println("\nAll Restaurants:");
        db.getRestaurants().forEach(r -> System.out.println("- " + r.getName()));
    }
}