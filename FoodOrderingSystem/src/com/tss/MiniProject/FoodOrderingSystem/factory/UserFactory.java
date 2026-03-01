package com.tss.MiniProject.FoodOrderingSystem.factory;

import com.tss.MiniProject.FoodOrderingSystem.model.Admin.Admin;
import com.tss.MiniProject.FoodOrderingSystem.model.Customer.Customer;
import com.tss.MiniProject.FoodOrderingSystem.model.DeliveryAgent.DeliveryAgent;
import com.tss.MiniProject.FoodOrderingSystem.model.User.AbstractUser;

import java.util.Map;

public class UserFactory {

    public static AbstractUser createUser(String role, Map<String, String> data) {
        return switch (role.toLowerCase()) {
            case "admin" -> new Admin.Builder()
                    .setId(data.get("id"))
                    .setName(data.get("name"))
                    .setUsername(data.get("username"))
                    .setPassword(data.get("password"))
                    .build();

            case "customer" -> new Customer.Builder()
                    .setId(data.get("id"))
                    .setName(data.get("name"))
                    .setUsername(data.get("username"))
                    .setPassword(data.get("password"))
                    .setEmail(data.getOrDefault("email", "unknown@example.com"))
                    .setAddress(data.getOrDefault("address", "Not Provided"))
                    .setPhoneNumber((data.getOrDefault("phone", "0")))
                    .build();

            case "deliveryagent" -> new DeliveryAgent.Builder()
                    .setId(data.get("id"))
                    .setName(data.get("name"))
                    .setUsername(data.get("username"))
                    .setPassword(data.get("password"))
                    .setVehicleNumber(data.getOrDefault("vehicle", "N/A"))
                    .setAddress(data.getOrDefault("address", "Unknown"))
                    .setAvailable(true)
                    .build();

            default -> throw new IllegalArgumentException("Invalid role: " + role);
        };
    }
}
