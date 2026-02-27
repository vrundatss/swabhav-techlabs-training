package FoodOrderingSystem.factory;

import com.tss.MiniProject.FoodOrderingSystem.model.Payment.PaymentProcessor;
import com.tss.MiniProject.FoodOrderingSystem.model.Payment.UPIPaymentProcessor;

public class UPIFactory implements PaymentFactory{
    @Override
    public PaymentProcessor createPaymentProcessor() {
        return new UPIPaymentProcessor();
    }
}
