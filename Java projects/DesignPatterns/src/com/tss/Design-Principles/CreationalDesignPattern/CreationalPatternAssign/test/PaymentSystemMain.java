package com.tss.CreationalDesignPattern.CreationalPatternAssign.test;

import com.tss.CreationalDesignPattern.CreationalPatternAssign.model.*;

import java.util.Scanner;

public class PaymentSystemMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== PAYMENT SYSTEM =====");
        System.out.println("Available Domains : ECOMMERCE, GAMING");
        System.out.print("Enter your Domain: ");
        String domain = scanner.nextLine().toUpperCase();

        PaymentFactory basePaymentFactory = DomainSelector.getFactory(domain);
        if (basePaymentFactory == null) {
            System.out.println("Invalid domain...\nExiting...");
            return;
        }
        System.out.println("Selected domain : " + basePaymentFactory.getDomain());

        System.out.println("\nAvailable Payment Methods : CREDITCARD, UPI, WALLET");
        System.out.print("Enter Payment Method: ");
        String method = scanner.nextLine().toUpperCase();

        IPaymentProcessor paymentProcessor = basePaymentFactory.processPayment(method);
        if (paymentProcessor == null) {
            System.out.println("Invalid payment method...\nExiting...");
            return;
        }

        System.out.print("\nEnter amount to pay: ");
        double amount = scanner.nextDouble();
        paymentProcessor.Pay(amount);

        System.out.println("Payment Completed Successfully...");
    }
}


//package com.tss.CreationalDesignPattern.CreationalPatternAssign.test;
//
//import com.tss.CreationalDesignPattern.CreationalPatternAssign.model.*;
//
//import java.util.Scanner;
//
//public class PaymentSystemMain {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
////        System.out.println("Enter Your Domain : ");
////        String domain = scanner.nextLine().toUpperCase();
//
//        PaymentFactory paymentFactory = null;
////        DomainSelector.getFactory(domain)
////        String domain = paymentFactory.getDomain();
//        BasePaymentFactory basePaymentFactory = new ECommFactory();
//
//        IPaymentProcessor iPaymentProcessor = basePaymentFactory.processPayment("UPI");
//        iPaymentProcessor.Pay(20000);
////        paymentProcessor.Pay(200);
//
////           DomainSelector.getFactory("GAMING");
////                basePaymentFactory.processPayment("UPI");
////                paymentProcessor.Pay(1000);
//
//    }
//}
