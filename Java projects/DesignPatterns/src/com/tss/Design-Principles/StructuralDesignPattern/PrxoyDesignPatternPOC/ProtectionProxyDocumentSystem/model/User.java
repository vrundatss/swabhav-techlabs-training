package com.tss.StructuralDesignPattern.PrxoyDesignPatternPOC.ProtectionProxyDocumentSystem.model;

public class User {
    private final String name;
    private final String role;
    private final String password;

    public User(String name, String role, String password) {
        this.name = name;
        this.role = role;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }
}
