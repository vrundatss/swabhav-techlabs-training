package FoodOrderingSystem.model.Payment;
import java.time.LocalDateTime;

public class CreditCardProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(Payment payment, double amount) {
        System.out.println("Credit Card Payment of ₹" + amount);
        payment.setStatus(new CompletedStatus());
        payment.setPaidAt(LocalDateTime.now());
        System.out.println("Credit Card Payment Successful!");
        return true;
    }
}
