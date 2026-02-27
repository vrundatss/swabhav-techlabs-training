package FoodOrderingSystem.model.Order;

public class CancelledStatus implements OrderStatus {

    @Override
    public void next(Order order) {

    }

    @Override
    public void cancel(Order order) {
        System.out.println("Order is Canceled");
    }

    @Override
    public String getName() {
        return "CANCELED";
    }
}
