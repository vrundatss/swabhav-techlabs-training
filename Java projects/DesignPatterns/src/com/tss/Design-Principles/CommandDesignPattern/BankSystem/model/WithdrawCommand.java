package com.tss.BehavioralDesignPattern.CommandDesignPattern.BankSystem.model;

public class WithdrawCommand implements Command{
    private Account account;
    private Double amount;

    public WithdrawCommand(Account account, Double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        account.withdraw(amount);
    }

    @Override
    public void undo() {
        account.deposit(amount);
        System.out.println("Undo withdraw of " + amount + " rs.");
    }
}
