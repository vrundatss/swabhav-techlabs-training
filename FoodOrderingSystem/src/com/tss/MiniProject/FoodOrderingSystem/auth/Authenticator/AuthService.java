package com.tss.MiniProject.FoodOrderingSystem.auth.Authenticator;

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

public class AuthService {
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
//    public Customer loginCustomer(String username, String plainPassword) {
//        return db.getCustomers().stream()
//                .filter(c -> c.getUsername().equals(username) &&
//                        c.getPassword().equals(PasswordUtil.hash(plainPassword)))
//                .findFirst()
//                .orElse(null);
//    }

    // --- ADMIN ---
    public Admin registerAdmin(String name, String username, String email, String plainPassword) {
        String id = "A-" + UUID.randomUUID().toString().substring(0, 6);
        String hash = PasswordUtil.hash(plainPassword);

        Admin a = new Admin.Builder()
                .id(id)
                .name(name)
                .username(username)
                .password(hash)
                .email(email)
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

//package com.tss.MiniProject.com.tss.MiniProject.FoodOrderingSystem.auth.Authenticator;
//
//import com.tss.MiniProject.com.tss.MiniProject.FoodOrderingSystem.model.Customer.Customer;
//import com.tss.MiniProject.com.tss.MiniProject.FoodOrderingSystem.model.Admin.Admin;
//import com.tss.MiniProject.com.tss.MiniProject.FoodOrderingSystem.model.DeliveryAgent.DeliveryAgent;
//import com.tss.MiniProject.com.tss.MiniProject.FoodOrderingSystem.repository.CustomerRepository;
//import com.tss.MiniProject.com.tss.MiniProject.FoodOrderingSystem.repository.AdminRepository;
//import com.tss.MiniProject.com.tss.MiniProject.FoodOrderingSystem.repository.DeliveryAgentRepository;
//import com.tss.MiniProject.com.tss.MiniProject.FoodOrderingSystem.util.PasswordUtil;
//
//import java.util.List;
//import java.util.UUID;
//
//public class AuthService {
//    private final CustomerRepository customerRepo;
//    private final AdminRepository adminRepo;
//    private final DeliveryAgentRepository agentRepo;
//
//    public AuthService(CustomerRepository customerRepo, AdminRepository adminRepo, DeliveryAgentRepository agentRepo) {
//        this.customerRepo = customerRepo;
//        this.adminRepo = adminRepo;
//        this.agentRepo = agentRepo;
//    }
//
//    // CUSTOMER
//    public Customer registerCustomer(String name,String username , String email, String plainPassword, String phone) {
//        // ensure email unique
//        if (customerRepo.findByUsername(username) != null) {
//            throw new IllegalArgumentException("Email already registered");
//        }
//        String id = "C" + UUID.randomUUID().toString().substring(0,8);
//        String hash = PasswordUtil.hash(plainPassword);
//        Customer c = new Customer.Builder()
//                .id(id)
//                .name(name)
//                .password(hash)
//                .email(email)
//                .build();
//        customerRepo.add(c);
//        return c;
//    }
//
//    public Customer loginCustomer(String username, String plainPassword) {
//        Customer c = customerRepo.findByUsername(username);
//        if (c == null) return null;
//        if (c.getPassword().equals(PasswordUtil.hash(plainPassword))) return c;
//        return null;
//    }
//
//    // ADMIN
//    public Admin registerAdmin(String name, String username , String email, String plainPassword) {
//        if (adminRepo.findByUsername(username) != null) {
//            throw new IllegalArgumentException("Admin email exists");
//        }
//        String id = "A" + UUID.randomUUID().toString().substring(0,6);
//        String hash = PasswordUtil.hash(plainPassword);
//        Admin a = new Admin.Builder().id(id).name(name).password(hash).email(email).build();
//        adminRepo.add(a);
//        return a;
//    }
//
//    public Admin loginAdmin(String username, String plainPassword) {
//        Admin admin = adminRepo.findByUsername(username);
//        if (admin == null) return null;
//        if (admin.getPassword().equals(PasswordUtil.hash(plainPassword))) return admin;
//        return null;
//    }
//
//    // DELIVERY AGENT
//    public DeliveryAgent registerDeliveryAgent(String name, String username,String email, String plainPassword, String phone) {
//        if (agentRepo.findByUsername(username) != null) {
//            throw new IllegalArgumentException("Delivery agent email exists");
//        }
//        String id = "D" + UUID.randomUUID().toString().substring(0,6);
//        String hash = PasswordUtil.hash(plainPassword);
//        DeliveryAgent d = new DeliveryAgent.Builder()
//                .id(id).name(name).password(hash).email(email).build();
//        agentRepo.add(d);
//        return d;
//    }
//
//    public DeliveryAgent loginDeliveryAgent(String username, String plainPassword) {
//        DeliveryAgent d = agentRepo.findByUsername(username);
//        if (d == null) return null;
//        if (d.getPassword().equals(PasswordUtil.hash(plainPassword))) return d;
//        return null;
//    }
//}
//
////package com.tss.MiniProject.com.tss.MiniProject.FoodOrderingSystem.auth.Authenticator;
////
////import java.util.HashMap;
////import java.util.Map;
////
////public class AuthService implements Authenticator {
////    private final Map<String, String> credentials = new HashMap<>();
////    private final Map<String, String> roles = new HashMap<>();
////    private final Map<String, Boolean> loggedInUsers = new HashMap<>();
////
////    @Override
////    public boolean register(String username, String password, String role) {
////        if (credentials.containsKey(username)) {
////            System.out.println("User already exists...");
////            return false;
////        }
////        credentials.put(username, password);
////        roles.put(username, role.toLowerCase());
////        System.out.println( role + " registered successfully!");
////        return true;
////    }
////
////    @Override
////    public boolean login(String username, String password) {
////        if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
////            loggedInUsers.put(username, true);
////            return true;
////        }
////        return false;
////    }
////
////    @Override
////    public void logout(String username) {
////        loggedInUsers.put(username, false);
////        System.out.println(username + " logged out successfully!");
////    }
////
////    @Override
////    public String getRole(String username) {
////        return roles.get(username);
////    }
////
////    public boolean isLoggedIn(String username) {
////        return loggedInUsers.getOrDefault(username, false);
////    }
////}