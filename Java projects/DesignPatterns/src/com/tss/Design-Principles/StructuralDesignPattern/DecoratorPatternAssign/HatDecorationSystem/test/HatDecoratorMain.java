package com.tss.StructuralDesignPattern.DecoratorPatternAssign.HatDecorationSystem.test;

import com.tss.StructuralDesignPattern.DecoratorPatternAssign.HatDecorationSystem.model.decorator.GoldenHat;
import com.tss.StructuralDesignPattern.DecoratorPatternAssign.HatDecorationSystem.model.decorator.RibbonHat;
import com.tss.StructuralDesignPattern.DecoratorPatternAssign.HatDecorationSystem.model.types.IHat;
import com.tss.StructuralDesignPattern.DecoratorPatternAssign.HatDecorationSystem.model.types.PremiumHat;
import com.tss.StructuralDesignPattern.DecoratorPatternAssign.HatDecorationSystem.model.types.StandardHat;

import java.util.Scanner;

public class HatDecoratorMain {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        IHat hat = null;

        System.out.println("\nAvailable Hat Types:");
        System.out.println("1. Standard Hat" + " (" + new StandardHat().getPrice() + ")");
        System.out.println("2. Premium Hat" + " (" + new PremiumHat().getPrice() + ")");
//        System.out.println("3. Custom Hat (Golden , Ribbon)");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice != 1 && choice != 2 && choice != 3) {
            System.out.println("Invalid Choice...");
            return;
        }

        if(choice == 1)
            hat = new StandardHat();
        else if (choice == 2)
            hat = new PremiumHat();


        System.out.print("\nWould you like to add decorations? (yes/no): ");
        String decorationChoice = scanner.nextLine();

        if (decorationChoice.equalsIgnoreCase("yes")) {
            System.out.print("Add Ribbon ("+ (new RibbonHat(hat).getPrice() - hat.getPrice())+  ") ? (yes/no): ");
            String ribbonChoice = scanner.nextLine();
            if (ribbonChoice.equalsIgnoreCase("yes")) {
                hat = new RibbonHat(hat);
            }

            System.out.print("Add Golden Design (" + (new GoldenHat(hat).getPrice() - hat.getPrice())  +") ? (yes/no): ");
            String goldChoice = scanner.nextLine();
            if (goldChoice.equalsIgnoreCase("yes")) {
                hat = new GoldenHat(hat);
            }
        }

        System.out.println("\nYour Final Choice ==> ");
        System.out.println("Name : " + hat.getName() + " Hat");
        System.out.println("Total Price : " + hat.getPrice() + " rs.");
        System.out.println("Description : " + hat.getDescription());
    }
}
