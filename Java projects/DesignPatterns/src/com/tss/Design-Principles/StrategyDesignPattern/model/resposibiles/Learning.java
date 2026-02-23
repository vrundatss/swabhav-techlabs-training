package com.tss.BehavioralDesignPattern.StrategyDesignPattern.model.resposibiles;

public class Learning implements IResponsibility{
    @Override
    public void perform() {
        System.out.println("---> Learn company processes and assist team members.");
    }
}
