package com.tss.BehavioralDesignPattern.ObserverDesignPattern.model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private Integer id;
    private Long accountNumber;
    private Double balance;
    private String holderName;

    List<INotifier> notifiers = new ArrayList<>();

    public Account(Integer id, Long accountNumber, Double balance, String holderName) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.holderName = holderName;
    }

    public void addNotifier(INotifier notifier) {
        notifiers.add(notifier);
    }

    public void removeNotifier(INotifier notifier) {
        notifiers.remove(notifier);
    }

    private void notifyObservers(String message) {
        for (INotifier notifier : notifiers) {
            notifier.sendAlert(message);
        }
    }

    public void deposit(Double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }

        balance += amount;
        System.out.println("Deposited Rs. " + amount + " to Account.");

        notifyObservers( amount + " rs. deposited successfully. \nCurrent Balance: " + balance + " rs.");

    }

    public void withdraw(Double amount){
        if (amount <= 0) {
            System.out.println("Withdraw amount must be positive.");
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient balance...");
            return;
        }

        balance -= amount;
        System.out.println("Withdrawn Rs. " + amount + " from Account.");

        notifyObservers( amount + " rs. withdrawn successfully. \nCurrent Balance: " + balance + " rs.");

    }
}
