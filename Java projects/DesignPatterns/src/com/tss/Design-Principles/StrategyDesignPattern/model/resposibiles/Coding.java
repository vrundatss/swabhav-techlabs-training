package com.tss.BehavioralDesignPattern.StrategyDesignPattern.model.resposibiles;

public class Coding implements IResponsibility{
    @Override
    public void perform() {
        System.out.println("---> Write, test, and debug code.");
    }
}
