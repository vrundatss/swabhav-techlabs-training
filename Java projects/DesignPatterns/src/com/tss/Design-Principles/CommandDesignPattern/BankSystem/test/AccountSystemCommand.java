package com.tss.BehavioralDesignPattern.CommandDesignPattern.BankSystem.test;

import com.tss.BehavioralDesignPattern.CommandDesignPattern.BankSystem.model.Account;
import com.tss.BehavioralDesignPattern.CommandDesignPattern.BankSystem.model.Command;
import com.tss.BehavioralDesignPattern.CommandDesignPattern.BankSystem.model.DepositCommand;
import com.tss.BehavioralDesignPattern.CommandDesignPattern.BankSystem.model.Invoker.CommandInvoker;
import com.tss.BehavioralDesignPattern.CommandDesignPattern.BankSystem.model.WithdrawCommand;

public class AccountSystemCommand {
    public static void main(String[] args) {
        Account account = new Account("AC12345678", 10000.0);

        System.out.println("\n===== Transaction History =====");

        Command deposit1 = new DepositCommand(account, 2000.0);
        Command withdraw1 = new WithdrawCommand(account, 1000.0);

        CommandInvoker invoker = new CommandInvoker();

        invoker.executeCommand(deposit1);
        invoker.executeCommand(withdraw1);

        System.out.println("\n===== Undo last command =====");
        invoker.undoLastCommand();
    }
}
