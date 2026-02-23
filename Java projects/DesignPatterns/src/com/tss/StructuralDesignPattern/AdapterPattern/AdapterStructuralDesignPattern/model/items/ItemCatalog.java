package com.tss.StructuralDesignPattern.AdapterPattern.AdapterStructuralDesignPattern.model.items;

import com.tss.StructuralDesignPattern.AdapterPattern.AdapterStructuralDesignPattern.model.adapter.HatAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ItemCatalog {

    private static List<Items> catalog = new ArrayList<>();


    public static void addItemsToCatalog() {
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("\nChoose Item Category to Add:");
            System.out.println("1. Biscuit");
            System.out.println("2. Chocolate");
            System.out.println("3. Hat");
            System.out.print("Enter your choice: ");
            int type = scanner.nextInt();
            scanner.nextLine();

            switch (type) {
                case 1: {
                    System.out.print("Enter Biscuit Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Biscuit Price: ");
                    double price = readDouble(scanner);

                    catalog.add(new Biscuit(name, price));
                    System.out.println("Biscuit added successfully!");
                    break;
                }

                case 2: {
                    System.out.print("Enter Chocolate Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Chocolate Price: ");
                    double price = readDouble(scanner);

                    catalog.add(new Chocolate(name, price));
                    System.out.println("Chocolate added successfully!");
                    break;
                }

                case 3: {
                    System.out.print("Enter Hat Short Name: ");
                    String shortName = scanner.nextLine();
                    System.out.print("Enter Hat Long Name: ");
                    String longName = scanner.nextLine();
                    System.out.print("Enter Hat Base Price: ");
                    double basePrice = readDouble(scanner);
                    System.out.print("Enter Hat Tax (%): ");
                    double tax = readDouble(scanner);

                    Hat hat = new Hat(shortName, longName, basePrice, tax);
                    catalog.add(new HatAdapter(hat));
                    System.out.println("Hat added successfully!");
                    break;
                }

                default:
                    System.out.println("Invalid choice! Please select 1–3.");
            }

            System.out.print("\nAdd more items? (y/n): ");
            choice = scanner.next();
            scanner.nextLine();
        } while (choice.equalsIgnoreCase("y"));
    }

    public static List<Items> getAvailableItems() {
        return catalog;
    }

    public static void displayCatalog(List<Items> availableItems) {
        System.out.println("\n========================================");
        System.out.println("             AVAILABLE ITEMS            ");
        System.out.println("========================================");
        System.out.printf("%-5s %-25s %10s%n", "ID", "Item Name", "Price (rs.)");
        System.out.println("----------------------------------------");

        int index = 1;
        for (Items item : availableItems) {
            System.out.printf("%-5d %-25s %10.2f%n", index++, item.getItemName(), item.getItemPrice());
        }
        System.out.println("========================================");
    }

    private static double readDouble(Scanner scanner) {
        while (true) {
            try {
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            } catch (Exception e) {
                System.out.print("Invalid number...please enter again: ");
                scanner.nextLine();
            }
        }
    }

    static {
        catalog.add(new Biscuit("GoodDay", 50.0));
        catalog.add(new Biscuit("Oreo", 60.0));
        catalog.add(new Chocolate("Dairy Milk", 110.0));
        catalog.add(new Chocolate("Nestle", 80.0));
        catalog.add(new HatAdapter(new Hat("WM", "Winter Hat", 200.0, 10.0)));
        catalog.add(new HatAdapter(new Hat("SM", "Summer Hat", 150.0, 8.0)));
    }


}
