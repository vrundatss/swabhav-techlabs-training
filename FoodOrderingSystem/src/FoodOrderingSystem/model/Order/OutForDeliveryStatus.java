package FoodOrderingSystem.model.Order;

public class OutForDeliveryStatus implements OrderStatus {
    @Override
    public void next(Order order) {
        order.setStatus(new DeliveredStatus());
    }

    @Override
    public void cancel(Order order) {
        System.out.println("Cannot cancel, order already out for delivery");
    }

    @Override
    public String getName() {
        return "OUT_FOR_DELIVERY";
    }
}