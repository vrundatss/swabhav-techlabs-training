package com.tss.BehavioralDesignPattern.StrategyDesignPattern.model.roles;

import com.tss.BehavioralDesignPattern.StrategyDesignPattern.model.resposibiles.IResponsibility;

import java.util.List;

public interface IRole {
    String getRoleName();
    List<IResponsibility> getResponsibilites();
}
