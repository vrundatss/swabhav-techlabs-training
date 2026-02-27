package FoodOrderingSystem.model.cart;

import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(MenuItem item, int quantity) {
        items.add(new CartItem(item, quantity));
    }

    public void removeItem(MenuItem item) {
        items.removeIf(cartItem -> cartItem.getItem().equals(item));
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(CartItem::getSubTotal).sum();
    }

    public void clear() { items.clear(); }
    public List<CartItem> getItems() { return items; }
}