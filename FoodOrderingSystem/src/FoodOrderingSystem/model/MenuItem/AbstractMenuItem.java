package FoodOrderingSystem.model.MenuItem;

import com.tss.MiniProject.FoodOrderingSystem.model.Inventory.Inventory;
import com.tss.MiniProject.FoodOrderingSystem.model.enums.ItemCategoryType;

public class AbstractMenuItem implements MenuItem, Inventory {
    private final String id;
    private final String name;
    private final Double price;
    private Integer stock;
    private final ItemCategoryType category;
    private final boolean isAvailable;

    public AbstractMenuItem(String id , String name, Double price, Integer stock, ItemCategoryType category, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
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

        System.out.printf("| %-10s | %-18s | %-15s | %-8d | %-9.2frs.| %-9.2frs.| %-10.2frs.|%n",
                id,
                name.length() > 18 ? name.substring(0, 15) + "..." : name,
                category.name(),
                stock,
                price,
                finalPrice,
                (stock * finalPrice));
    }
    @Override
    public Integer getStock() {
        return this.stock;
    }

    @Override
    public void reduceStock(Integer quantity) {
        if(quantity <= stock)
            stock -= quantity;

        throw new IllegalStateException(name + " is out of stock!!!...");
    }

    public ItemCategoryType getCategory() {
        return category;
    }

    @Override
    public void addStock(Integer quantity) {
        stock += quantity;
    }

    @Override
    public boolean isAvailable() {
        return (stock > 0);
    }


}
