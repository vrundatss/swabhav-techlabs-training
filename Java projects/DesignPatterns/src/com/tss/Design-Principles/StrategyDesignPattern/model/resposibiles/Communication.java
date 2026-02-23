package com.tss.BehavioralDesignPattern.StrategyDesignPattern.model.resposibiles;

public class Communication implements IResponsibility{
    @Override
    public void perform() {
        System.out.println("- Communicate with clients and stakeholders.");
    }
}
