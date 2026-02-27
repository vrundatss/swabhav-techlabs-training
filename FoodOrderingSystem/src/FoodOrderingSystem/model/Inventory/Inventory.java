package FoodOrderingSystem.model.Inventory;


public interface Inventory {
    Integer getStock();
    void reduceStock(Integer quantity);
    void addStock(Integer quantity);
    boolean isAvailable();
}