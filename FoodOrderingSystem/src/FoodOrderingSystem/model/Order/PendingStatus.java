package FoodOrderingSystem.model.Order;

public class PendingStatus implements OrderStatus{
    @Override
    public void next(Order order) {
        order.setStatus(new AssignedStatus());
    }

    @Override
    public void cancel(Order order) {
        order.setStatus(new CancelledStatus());
    }

    @Override
    public String getName() {
        return "PENDING";
    }
}
