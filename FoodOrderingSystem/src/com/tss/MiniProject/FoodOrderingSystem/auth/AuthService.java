package com.tss.MiniProject.FoodOrderingSystem.auth;

import com.tss.MiniProject.FoodOrderingSystem.controller.AppFacade;
import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
import com.tss.MiniProject.FoodOrderingSystem.exceptions.EmailAlreadyExistsException;
import com.tss.MiniProject.FoodOrderingSystem.exceptions.PhoneNumberAlreadyExistsException;
import com.tss.MiniProject.FoodOrderingSystem.factory.UserFactory;
import com.tss.MiniProject.FoodOrderingSystem.model.Admin.Admin;
import com.tss.MiniProject.FoodOrderingSystem.model.Customer.Customer;
import com.tss.MiniProject.FoodOrderingSystem.model.DeliveryAgent.DeliveryAgent;
import com.tss.MiniProject.FoodOrderingSystem.util.PasswordUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuthService{
    private AppFacade facade;

    private final DataStore db = DataStore.getInstance();

    public AuthService() {
    }

    public void setFacade(AppFacade facade) {
        this.facade = facade;
    }

    // --- CUSTOMER ---
    public Customer registerCustomer(String name, String username, String email, String plainPassword , String address , String phone) throws PhoneNumberAlreadyExistsException {
        boolean isExistsUsername = db.getCustomers().stream()
                .anyMatch(c -> username.equalsIgnoreCase(c.getUsername()));
        if (isExistsUsername) {
            throw new IllegalArgumentException("This Username already taken!!!");
        }

        boolean isExistsEmail = db.getCustomers().stream()
                .anyMatch(c -> email.equalsIgnoreCase(c.getEmail()));
        if (isExistsEmail) {
            throw new EmailAlreadyExistsException("This Email is already registered!!!");
        }

        boolean isExistsPhone = db.getCustomers().stream()
                .anyMatch(c -> phone.equalsIgnoreCase(c.getPhoneNumber()));
        if (isExistsPhone) {
            throw new PhoneNumberAlreadyExistsException("This Phone Number is already registered!!!");
        }

        String id = "C-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        Map<String, String> customerData = new HashMap<>();
        customerData.put("id", id);
        customerData.put("name", name);
        customerData.put("username", username);
        customerData.put("email", email);
        customerData.put("phone", phone);
        customerData.put("password", plainPassword);
        customerData.put("address", address);

        Customer newCustomer = (Customer) UserFactory.createUser("customer", customerData);

        facade.getCustomerService().registerCustomer(newCustomer);

        System.out.println("New Customer Registered: " + newCustomer.getUsername() + " (" + newCustomer.getId() + ")");
        return newCustomer;
    }

    public Customer loginCustomer(String username, String plainPassword) {

//        String hashedInput = PasswordUtil.hash(plainPassword);

        return db.getCustomers().stream()
                .filter(c -> c.getUsername().equals(username) &&
                        c.getPassword().equals(plainPassword))
                .findFirst()
                .orElse(null);
    }


    // --- ADMIN ---
    public Admin registerAdmin(String name, String username, String email, String plainPassword) {
        String id = "A-" + UUID.randomUUID().toString().substring(0, 6);
        String hash = PasswordUtil.hash(plainPassword);

        Admin a = new Admin.Builder()
                .setId(id)
                .setName(name)
                .setUsername(username)
                .setPassword(hash)
                .setEmail(email)
                .build();

        db.getAdmins().add(a);
        return a;
    }

    public Admin loginAdmin(String username, String password) {
        return DataStore.getInstance().getAdmins().stream()
                .filter(a -> a.getUsername().equalsIgnoreCase(username)
                        && a.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    // --- DELIVERY AGENT ---
    public DeliveryAgent registerDeliveryAgent(String name, String email,String phone , String address, String vehicleNumber, String password) {
        boolean exists = db.getAgents().stream()
                .anyMatch(a -> a.getEmail().equalsIgnoreCase(email));
        if (exists) {
            throw new EmailAlreadyExistsException("Delivery Agent with this email already exists!");
        }

        String id = "A-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();

        DeliveryAgent newAgent = new DeliveryAgent.Builder()
                .setId(id)
                .setName(name)
                .setEmail(email)
                .setPhoneNumber(phone)
                .setPassword(password)
                .setAddress(address)
                .setVehicleNumber(vehicleNumber)
                .setAvailable(true)
                .setEarnings(0.0)
                .build();

        db.getAgents().add(newAgent);
        System.out.println("Registration successful! Agent ID: " + id);
        return newAgent;
    }

    public DeliveryAgent loginDeliveryAgent(String email, String plainPassword) {
        return db.getAgents().stream()
                .filter(d -> d.getEmail() != null
                        && d.getEmail().equalsIgnoreCase(email)
                        && d.getPassword() != null
                        && d.getPassword().equals(plainPassword))
                .findFirst()
                .orElse(null);
    }
}