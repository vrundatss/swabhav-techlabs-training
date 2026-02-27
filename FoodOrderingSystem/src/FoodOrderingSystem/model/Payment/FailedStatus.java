package FoodOrderingSystem.model.Payment;

public class FailedStatus implements PaymentStatus {

    @Override
    public void next(Payment payment) {
        System.out.println("Retrying payment...");
        payment.setStatus(new PendingStatus());
    }

    @Override
    public void fail(Payment payment) {
        System.out.println("Payment already failed. Please retry or choose another method.");
    }

    @Override
    public String getName() {
        return "FAILED";
    }
}
