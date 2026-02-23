package com.tss.CreationalDesignPattern.BuilderPatternPOC.test;

import com.tss.CreationalDesignPattern.BuilderPatternPOC.model.CreditCardPayment;
import com.tss.CreationalDesignPattern.BuilderPatternPOC.model.Payment;
import com.tss.CreationalDesignPattern.BuilderPatternPOC.model.UPIPayment;
import com.tss.CreationalDesignPattern.BuilderPatternPOC.processor.PaymentProcessor;

public class PaymentBuilderMain {
    public static void main(String[] args) {
        Payment creditCardPayment = new CreditCardPayment
                .CreditCardPaymentBuilder("1343421321" , 1029033421, 2000.0)
                .setCardHolder("Vrunda Chavda")
                .setCurrency("INR")
                .setEmail("abc@gmail.com")
                .setPhoneNo(1122334455)
                .build();


//        creditCardPayment.processPayment();

        PaymentProcessor paymentProcessor = new PaymentProcessor(creditCardPayment);

        paymentProcessor.makePayment();

        Payment upiPayment = new UPIPayment.UPIPaymentBuilder("223419478" , "xyz@upi" , 5000.0 )
                .setCurrency("INR")
                .setEmail("xyz@gmail.com")
                .build();

        paymentProcessor = new PaymentProcessor(upiPayment);
        paymentProcessor.makePayment();
//        upiPayment.processPayment();
    }
}
