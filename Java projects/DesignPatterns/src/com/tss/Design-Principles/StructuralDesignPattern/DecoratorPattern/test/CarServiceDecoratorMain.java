package com.tss.StructuralDesignPattern.DecoratorPattern.test;

import com.tss.StructuralDesignPattern.DecoratorPattern.model.CarInspection;
import com.tss.StructuralDesignPattern.DecoratorPattern.model.ICarService;
import com.tss.StructuralDesignPattern.DecoratorPattern.model.OilChange;
import com.tss.StructuralDesignPattern.DecoratorPattern.model.WheelAlign;

import java.util.Scanner;

public class CarServiceDecoratorMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Base Service: rs. 1000" );
        System.out.println("\nWould you like to add extra services?");
        System.out.println("1. Oil Change (500 rs.)");
        System.out.println("2. Wheel Alignment (400 rs.)");
        System.out.println("3. Both");
        System.out.println("4. None");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        ICarService car  = new CarInspection();

        OilChange oilChange = null;
        WheelAlign wheelAlign = null;

        Double totalAmount = car.getCost();

        switch (choice) {
            case 1:
                oilChange = new OilChange(car);
                totalAmount = oilChange.getCost();
                break;
            case 2:
                wheelAlign = new WheelAlign(car);
                totalAmount = wheelAlign.getCost();
                break;
            case 3:
                car = new WheelAlign(new OilChange(car));
                totalAmount = car.getCost();
                break;
        }

//                car = new OilChange(car);
//                car = new WheelAlign(car);

        System.out.println("Total amount : " + totalAmount);

        }
}
