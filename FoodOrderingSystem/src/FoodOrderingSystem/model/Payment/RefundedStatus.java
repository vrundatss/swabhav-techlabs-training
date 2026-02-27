package FoodOrderingSystem.model.Payment;

public class RefundedStatus implements PaymentStatus {
    @Override
    public void next(Payment payment) {
        System.out.println("Refunded payment cannot move to another state.");
    }

    @Override
    public void fail(Payment payment) {
        System.out.println("Refunded payment cannot fail.");
    }

    @Override
    public String getName() {
        return "REFUNDED";
    }
}