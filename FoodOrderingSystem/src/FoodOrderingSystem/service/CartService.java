package FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;

import java.util.HashMap;
import java.util.Map;

public class CartService {

    private final DataStore db =DataStore.getInstance();

    public void addToCart(int customerId, String itemId, int qty) {
        db.getUserCarts().computeIfAbsent(customerId, k -> new HashMap<>())
                .merge(itemId, qty, Integer::sum);
    }

    public Map<String, Integer> getCart(int customerId) {
        return db.getUserCarts().getOrDefault(customerId, new HashMap<>());
    }

    public void clearCart(int customerId) {
        db.getUserCarts().remove(customerId);
    }
}