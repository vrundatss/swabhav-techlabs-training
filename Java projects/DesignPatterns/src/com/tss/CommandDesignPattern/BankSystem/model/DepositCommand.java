package com.tss.BehavioralDesignPattern.CommandDesignPattern.BankSystem.model;

public class DepositCommand implements Command{
    private Account account;
    private Double amount;

    public DepositCommand(Account account, Double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        account.deposit(amount);
    }

    @Override
    public void undo() {
        account.withdraw(amount);
        System.out.println("Undo deposit of " + amount + " rs.");
    }
}
