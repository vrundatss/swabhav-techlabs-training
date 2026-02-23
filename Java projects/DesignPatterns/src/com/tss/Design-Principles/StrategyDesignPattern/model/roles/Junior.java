package com.tss.BehavioralDesignPattern.StrategyDesignPattern.model.roles;

import com.tss.BehavioralDesignPattern.StrategyDesignPattern.model.resposibiles.Coding;
import com.tss.BehavioralDesignPattern.StrategyDesignPattern.model.resposibiles.Communication;
import com.tss.BehavioralDesignPattern.StrategyDesignPattern.model.resposibiles.IResponsibility;
import com.tss.BehavioralDesignPattern.StrategyDesignPattern.model.resposibiles.Learning;

import java.util.List;

public class Junior implements IRole{
    @Override
    public String getRoleName() {
        return " Junior Developer";
    }

    @Override
    public List<IResponsibility> getResponsibilites() {
        return List.of(new Coding() , new Learning());
    }
}
