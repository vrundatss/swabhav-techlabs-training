package FoodOrderingSystem.factory;

import com.tss.MiniProject.FoodOrderingSystem.model.Payment.CreditCardProcessor;
import com.tss.MiniProject.FoodOrderingSystem.model.Payment.PaymentProcessor;

public class CreditCardFactory implements PaymentFactory {
    @Override
    public PaymentProcessor createPaymentProcessor() {
        return new CreditCardProcessor();
    }
}