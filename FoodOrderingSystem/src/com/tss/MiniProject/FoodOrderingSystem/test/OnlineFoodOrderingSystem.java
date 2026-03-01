package com.tss.MiniProject.FoodOrderingSystem.test;

import com.tss.MiniProject.FoodOrderingSystem.controller.AdminController;
import com.tss.MiniProject.FoodOrderingSystem.controller.AppFacade;
import com.tss.MiniProject.FoodOrderingSystem.controller.CustomerController;
import com.tss.MiniProject.FoodOrderingSystem.controller.DeliveryAgentController;
import com.tss.MiniProject.FoodOrderingSystem.data.MenuInitializer;
import com.tss.MiniProject.FoodOrderingSystem.data.UserInitializer;
import com.tss.MiniProject.FoodOrderingSystem.model.Admin.Admin;

import java.util.Scanner;

public class OnlineFoodOrderingSystem {

    public static void main(String[] args) {
        UserInitializer.loadDefaultUsers();
        MenuInitializer.loadDefaultMenu();

        // 1. Initialize the Facade (Coordinates all services and DataStore)
        AppFacade facade = AppFacade.getInstance();
        Scanner sc = new Scanner(System.in);

        // 2. Initialize Controllers with the shared Facade
        AdminController adminCtrl = new AdminController(facade, sc);
        CustomerController customerCtrl = new CustomerController(facade, sc);
        DeliveryAgentController deliveryCtrl = new DeliveryAgentController(facade, sc);

        System.out.println("******************************************");
        System.out.println("* WELCOME TO THE FOOD ORDERING SYSTEM  *");
        System.out.println("******************************************");

        while (true) {
            System.out.println("\n============== MAIN PANEL ==============");
            System.out.println("1. ADMIN PANEL (Management & Revenue)");
            System.out.println("2. CUSTOMER PANEL (Ordering & Tracking)");
            System.out.println("3. DELIVERY AGENT PANEL");
            System.out.println("0. EXIT SYSTEM");
            System.out.print("Please select your role: ");

            int choice;
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine(); // Clear the buffer
                continue;
            }

            if (choice == 0) {
                System.out.println("Thank you for using our system. Goodbye!");
                break;
            }

            sc.nextLine();
            switch (choice) {
                case 1 -> adminCtrl.handleAdminLogin(sc);
                case 2 -> customerCtrl.authMenu();
                case 3 -> deliveryCtrl.authMenu();
            }
        }
    }
}