package com.tss.MiniProject.FoodOrderingSystem.model.Admin;

import com.tss.MiniProject.FoodOrderingSystem.model.Order.OrderObserver;
import com.tss.MiniProject.FoodOrderingSystem.model.User.AbstractUser;

import java.util.ArrayList;
import java.util.List;

public class Admin extends AbstractUser implements OrderObserver {

    private final String adminLevel;
    private final String name;
    private final String email;

    private List<String> notifications = new ArrayList<>();

    private Admin(Builder builder) {
        super(builder);
        this.adminLevel = builder.adminLevel != null ? builder.adminLevel : "SUPER";
        this.name = builder.name;
        this.email = builder.email;
    }

    @Override
    public String getRole() {
        return "Admin";
    }

    public String getAdminLevel() {
        return adminLevel;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public static class Builder extends AbstractUser.Builder<Builder> {
        private String adminLevel;
        private String name;
        private String email;

        public Builder setAdminLevel(String adminLevel) {
            this.adminLevel = adminLevel;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public Admin build() {
            return new Admin(this);
        }
    }

    public List<String> getNotifications() {
        return notifications;
    }

    @Override
    public void update(String message) {
        notifications.add(message);
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-15s | %-12s | %-20s | %-20s | %-10s |",
                getId(),
                name,
                getUsername(),
                email,
                adminLevel,
                getRole());
    }
}