package com.tss.BehavioralDesignPattern.StrategyDesignPattern.model.resposibiles;

public class Management implements IResponsibility{
    @Override
    public void perform() {
        System.out.println("---> Manage team performance and approve leaves.");
    }
}
