package FoodOrderingSystem.model.Payment;

import java.time.LocalDateTime;

public class CODPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(Payment payment, double amount) {
        System.out.println("COD selected — payment will be collected on delivery.");
        payment.setStatus((PaymentStatus) new PendingStatus());
        payment.setPaidAt(LocalDateTime.now());
        return true;
    }
}
