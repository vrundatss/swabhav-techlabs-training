package com.tss.BehavioralDesignPattern.StrategyDesignPattern.model.roles;

import com.tss.BehavioralDesignPattern.StrategyDesignPattern.model.resposibiles.*;

import java.util.List;

public class Manager implements IRole{
    @Override
    public String getRoleName() {
        return " Reporting Manager ";
    }

    @Override
    public List<IResponsibility> getResponsibilites() {
        return List.of(new Coding() , new Learning() , new Communication() , new Management());
    }
}
