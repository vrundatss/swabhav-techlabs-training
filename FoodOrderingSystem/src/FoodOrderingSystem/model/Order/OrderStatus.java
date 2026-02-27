package FoodOrderingSystem.model.Order;


public interface OrderStatus {
    void next(Order order);
    void cancel(Order order);
    String getName();
}