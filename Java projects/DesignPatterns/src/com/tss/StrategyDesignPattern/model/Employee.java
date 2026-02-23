package com.tss.BehavioralDesignPattern.StrategyDesignPattern.model;

import com.tss.BehavioralDesignPattern.StrategyDesignPattern.model.resposibiles.IResponsibility;
import com.tss.BehavioralDesignPattern.StrategyDesignPattern.model.roles.IRole;

public class Employee {
    private String name;
    private IRole role;

    public Employee(String name, IRole role) {
        this.name = name;
        this.role = role;
    }

    public void showDetails() {
        System.out.println("\nEmployee Name: " + name);
        System.out.println("Current Role: " + role.getRoleName());
        System.out.println("Responsibilities:");
        for (IResponsibility r : role.getResponsibilites()) {
            r.perform();
        }
    }

    public void changeRole(IRole newRole) {
        this.role = newRole;
        System.out.println("\nRole changed successfully for " + name + " ===> " + newRole.getRoleName());
    }
}
