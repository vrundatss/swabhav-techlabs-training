package FoodOrderingSystem.factory;

import com.tss.MiniProject.FoodOrderingSystem.model.Payment.CODPaymentProcessor;
import com.tss.MiniProject.FoodOrderingSystem.model.Payment.PaymentProcessor;

public class CODFactory implements PaymentFactory{
    @Override
    public PaymentProcessor createPaymentProcessor() {
        return new CODPaymentProcessor();
    }
}
