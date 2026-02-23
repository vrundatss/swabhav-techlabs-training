package com.tss.BehavioralDesignPattern.CommandDesignPattern.BankSystem.model;

public class Account {
    private String accountNumber;
    private Double balance;

    public Account(String accountNumber, Double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(Double amount) {
        balance += amount;
        System.out.println("Deposited " + amount + " rs.  ---> Balance: " + balance + " rs.");
    }

    public void withdraw(Double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdraw " + amount + " rs.  ---> Balance: " + balance + " rs.");
        } else {
            System.out.println("Insufficient balance...");
        }
    }

    public Double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
