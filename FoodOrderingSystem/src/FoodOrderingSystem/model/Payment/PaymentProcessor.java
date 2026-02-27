package FoodOrderingSystem.model.Payment;

public interface PaymentProcessor {
    boolean processPayment(Payment payment, double amount);
}