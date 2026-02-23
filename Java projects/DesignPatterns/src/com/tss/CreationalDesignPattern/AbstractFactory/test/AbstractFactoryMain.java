package com.tss.CreationalDesignPattern.AbstractFactory.test;

import com.tss.CreationalDesignPattern.AbstractFactory.enums.CarType;
import com.tss.CreationalDesignPattern.AbstractFactory.model.*;

import java.util.Scanner;

public class AbstractFactoryMain {
    public static void main(String[] args) {
        CarType[] carTypes = CarType.values();
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Available Car Types =====");
        for (int i = 0; i < carTypes.length; i++) {
            System.out.println((i + 1) + ". " + carTypes[i].name());
        }

        System.out.print("Enter your choice : ");
        int choice = scanner.nextInt();

        CarType selectedType = carTypes[choice - 1];

        CarFactory carFactory = null;
        switch (selectedType){
            case TATA ->   carFactory= new TataFactory();
            case MARUTI -> carFactory = new MarutiFactory();
            case HYUNDAI -> carFactory = new HyundaiFactory();
        }

        Car car =  carFactory.getCar();
        car.drive();
    }
}


