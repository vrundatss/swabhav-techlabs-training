package FoodOrderingSystem.model.Order;

public class AssignedStatus implements OrderStatus {
    @Override
    public void next(Order order) {
        order.setStatus(new OutForDeliveryStatus());
    }

    @Override
    public void cancel(Order order) {
        order.setStatus(new CancelledStatus());
    }

    @Override
    public String getName() {
        return "ASSIGNED";
    }
}