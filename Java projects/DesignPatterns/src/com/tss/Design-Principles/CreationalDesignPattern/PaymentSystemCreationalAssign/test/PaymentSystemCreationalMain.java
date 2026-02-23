package com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.test;

import com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.Domain.ECommerceFactory;
import com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.Domain.GamingFactory;
import com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.Domain.IDomainFactory;
import com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.PaymentMethods.IPaymentMethodFactory;
import com.tss.CreationalDesignPattern.PaymentSystemCreationalAssign.model.PaymentProcessor.IPaymentProcessor;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PaymentSystemCreationalMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<IDomainFactory> domainFactories = Arrays.asList(
                new ECommerceFactory(),
                new GamingFactory()
        );

        System.out.println("===== PAYMENT SYSTEM =====");
        System.out.println("Available Domains:");
        domainFactories.forEach( domain -> System.out.println("=> " + domain.getDomainName()));

        System.out.print("Enter your Domain: ");
        String domainInput = scanner.nextLine().toUpperCase();

        IDomainFactory selectedDomain = domainFactories.stream()
                .filter(domain -> domain.getDomainName().equalsIgnoreCase(domainInput))
                .findFirst()
                .orElse(null);

        if (selectedDomain == null) {
            System.out.println("Invalid Domain...");
            return;
        }

        System.out.println("\nSelected Domain : " + selectedDomain.getDomainName());

        System.out.println("Available Payment Methods :");
        selectedDomain.getSupportedPaymentFactories().forEach(
                methodFactory -> System.out.println("=> " + methodFactory.getMethod())
        );

        System.out.print("\nEnter Payment Method : ");
        String methodInput = scanner.nextLine().trim().toUpperCase();

        IPaymentMethodFactory selectedMethod = selectedDomain.getSupportedPaymentFactories().stream()
                .filter(methodFactory -> methodFactory.getMethod().equalsIgnoreCase(methodInput))
                .findFirst()
                .orElse(null);

        if (selectedMethod == null) {
            System.out.println("Invalid Payment Method...");
            return;
        }

        System.out.print("\nEnter Amount to Pay : ");
        double amount = scanner.nextDouble();

        IPaymentProcessor processor = selectedMethod.paymentProcessor();

        processor.pay(amount);
        System.out.println("Payment Completed Successfully!!!...");
    }
}
