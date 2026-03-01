package com.tss.MiniProject.FoodOrderingSystem.data;

import com.tss.MiniProject.FoodOrderingSystem.controller.AppFacade;
import com.tss.MiniProject.FoodOrderingSystem.exceptions.PhoneNumberAlreadyExistsException;
import com.tss.MiniProject.FoodOrderingSystem.factory.UserFactory;
import com.tss.MiniProject.FoodOrderingSystem.model.Admin.Admin;
import com.tss.MiniProject.FoodOrderingSystem.model.Customer.Customer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserInitializer {

    public static void loadDefaultUsers() {
        DataStore db = DataStore.getInstance();
        AppFacade facade = AppFacade.getInstance();

        if (db.getCustomers().isEmpty()) {

            Map<String, String> c1 = new HashMap<>();
            c1.put("id", "C-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase());
            c1.put("name", "Vrunda Chavda");
            c1.put("username", "vrunda_123");
            c1.put("email", "vrunda@gmail.com");
            c1.put("password", "Vrunda@123");
            c1.put("phone", "9234567898");
            c1.put("address", "Nana Mava Circle, Rajkot");

            Map<String, String> c2 = new HashMap<>();
            c2.put("id", "C-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase());
            c2.put("name", "Tulsi Chavda");
            c2.put("username", "tulsi_123");
            c2.put("email", "tulsi@gmail.com");
            c2.put("password", "Tulsi@123");
            c1.put("phone", "6234567898");
            c2.put("address", "Indira Circle, Rajkot");

            Customer customer1 = (Customer) UserFactory.createUser("customer", c1);
            Customer customer2 = (Customer) UserFactory.createUser("customer", c2);

            try {
                facade.getAuthService().registerCustomer(
                        customer1.getUsername(), customer1.getUsername(),
                        customer1.getEmail(), customer1.getPassword() , customer1.getAddress() , customer1.getPhoneNumber()
                );
            } catch (PhoneNumberAlreadyExistsException e) {
                System.out.println(e.getMessage());
            }

            try {
                facade.getAuthService().registerCustomer(
                        customer2.getUsername(), customer2.getUsername(),
                        customer2.getEmail(), customer2.getPassword() ,
                        customer2.getAddress() , customer2.getPhoneNumber()
                );
            } catch (PhoneNumberAlreadyExistsException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Default Customers Registered: " + db.getCustomers().size());
        }

        if (db.getAgents().isEmpty()) {
            var authService = facade.getAuthService();

            authService.registerDeliveryAgent(
                    "Delivery Agent 1",
                    "d1@gmail.com",
                    "6123123123",
                    "Nana Mava Circle, Rajkot",
                    "GJ01-AB-1234",
                    "Agent@123"
            );

            authService.registerDeliveryAgent(
                    "Delivery Agent 2",
                    "d2@gmail.com",
                        "9123456789",
                    "K.K.V Hall, Rajkot",
                    "GJ02-CD-5678",
                    "Agent@123"
            );

            System.out.println("Default Delivery Agents Registered: " + db.getAgents().size());
        }

        if (db.getAdmins().isEmpty()) {

            Map<String, String> adminData = new HashMap<>();
            adminData.put("id", "ADM-001");
            adminData.put("name", "System Admin");
            adminData.put("username", "admin");
            adminData.put("password", "admin");

            Admin admin = (Admin) UserFactory.createUser("admin", adminData);

            facade.getAdminService().addAdmin(admin);
            System.out.println("Default Admin Registered");
            facade.getNotificationService().subscribeAdmin(admin);
        }

        System.out.println("\n=== Default User Initialization Completed ===\n");
    }
}