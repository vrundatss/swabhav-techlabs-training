package FoodOrderingSystem.model.cart;

import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.MenuItem;

public class CartItem {
    private MenuItem item;
    private int quantity;

    public CartItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
    public double getSubTotal() {
        return item.getPrice() * quantity;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}
