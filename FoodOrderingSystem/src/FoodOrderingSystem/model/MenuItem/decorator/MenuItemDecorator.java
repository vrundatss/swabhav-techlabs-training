package FoodOrderingSystem.model.MenuItem.decorator;

import com.tss.MiniProject.FoodOrderingSystem.model.Inventory.Inventory;
import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.MenuItem;

public abstract class MenuItemDecorator implements MenuItem, Inventory {
    final MenuItem item;

    public MenuItemDecorator(MenuItem item) {
        this.item = item;
    }

    @Override
    public String getName() {
        return item.getName();
    }

    @Override
    public Double getPrice() {
        return item.getPrice();
    }

    @Override
    public void display() {
        System.out.printf("- %-25s : %.2f%n", getName(), getPrice());
    }

    @Override
    public Integer getStock() {
        return (item instanceof  Inventory inventory) ? inventory.getStock() : 0;
    }

    @Override
    public void addStock(Integer quantity) {
        if(item instanceof Inventory inventory)
            inventory.addStock(quantity);
    }

    @Override
    public void reduceStock(Integer quantity) {
        if(item instanceof Inventory inventory)
            inventory.reduceStock(quantity);
    }

    @Override
    public boolean isAvailable() {
        return (item instanceof  Inventory inventory) && inventory.isAvailable();
    }
}
