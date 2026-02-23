package com.tss.BehavioralDesignPattern.CommandDesignPattern.BankSystem.model.Invoker;

import com.tss.BehavioralDesignPattern.CommandDesignPattern.BankSystem.model.Command;

import java.util.Stack;

public class CommandInvoker {

    private Stack<Command> history = new Stack<>();

    public void executeCommand(Command command){
        command.execute();
        history.push(command);
    }

    public void undoLastCommand(){
        if(history.empty()){
            System.out.println("No commands to undo...");
            return;
        }
        Command command = history.pop();
        command.undo();

    }
}
