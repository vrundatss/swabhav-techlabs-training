package com.tss.BehavioralDesignPattern.StrategyDesignPattern.test;

import com.tss.BehavioralDesignPattern.StrategyDesignPattern.model.Employee;
import com.tss.BehavioralDesignPattern.StrategyDesignPattern.model.roles.*;

import java.util.Scanner;

public class EmployeeResponsibilitesMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine();


        System.out.println("Select Current Role:");
        System.out.println("1. Intern");
        System.out.println("2. Junior Developer");
        System.out.println("3. Senior Developer");
        System.out.println("4. Manager");
        System.out.print("Enter your choice: ");
        int roleChoice = sc.nextInt();

        IRole initialRole = null;

        switch (roleChoice) {
            case 1 -> initialRole = new Intern();
            case 2 -> initialRole = new Junior();
            case 3 -> initialRole = new Senior();
            case 4 -> initialRole = new Manager();
            default -> {
                System.out.println("Invalid choice! Defaulting to Intern Role.");
                initialRole = new Intern();
            }
        }

        // Create employee with the chosen role
        Employee emp = new Employee(name, initialRole);
        emp.showDetails();


        while (true) {
            System.out.println("\nChange Role To:");
            System.out.println("1. Intern");
            System.out.println("2. Junior Developer");
            System.out.println("3. Senior Developer");
            System.out.println("4. Manager");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> emp.changeRole(new Intern());
                case 2 -> emp.changeRole(new Junior());
                case 3 -> emp.changeRole(new Senior());
                case 4 -> emp.changeRole(new Manager());
                case 0 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice...");
            }

            emp.showDetails();
        }
    }
}
