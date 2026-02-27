package FoodOrderingSystem.model.Payment;

public class PendingStatus implements PaymentStatus {

    @Override
    public void next(Payment payment) {
        System.out.println("Payment processed successfully!");
        payment.setStatus(new CompletedStatus());
    }

    @Override
    public void fail(Payment payment) {
        System.out.println("Payment failed while pending.");
        payment.setStatus(new FailedStatus());
    }

    @Override
    public String getName() {
        return "PENDING";
    }
}