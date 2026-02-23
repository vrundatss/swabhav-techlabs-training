
package com.tss.BehavioralDesignPattern.ObserverDesignPattern.test;

import com.tss.BehavioralDesignPattern.ObserverDesignPattern.model.*;
import java.util.Scanner;

public class AccountObserverMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account account = new Account(1 , 1243124141L , 2000.0 , "Vrunda");

        System.out.println("Choose notification options:");

        System.out.print("Enable SMS Notifications? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            account.addNotifier(new SMSNotifier());
        }

        System.out.print("Enable Email Notifications? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            account.addNotifier(new EmailNotifier());
        }

        System.out.print("Enable WhatsApp Notifications? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            account.addNotifier(new WhatsappNotifier());
        }

        System.out.println("Choose operation to perform:");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");

        int choice = scanner.nextInt();

        if(choice != 1 && choice != 2){
            System.out.println("Enter valid choice...");
            return;
        }

        if(choice == 1){
            System.out.print("\nEnter amount to deposit: ");
            double depositAmount = scanner.nextDouble();
            account.deposit(depositAmount);
        } else if (choice == 2) {
            System.out.print("\nEnter amount to withdraw: ");
            double withdrawAmount = scanner.nextDouble();
            account.withdraw(withdrawAmount);
        }

    }
}
