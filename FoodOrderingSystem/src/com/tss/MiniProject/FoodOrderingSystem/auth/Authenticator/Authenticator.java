package com.tss.MiniProject.FoodOrderingSystem.auth.Authenticator;

public interface Authenticator {
    boolean login(String username, String password);
    boolean register(String username, String password, String role);
    String getRole(String username);
    void logout(String username);
}