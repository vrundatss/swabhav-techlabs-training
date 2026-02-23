package com.tss.BehavioralDesignPattern.StateDesignPattern.test;

import com.tss.BehavioralDesignPattern.StateDesignPattern.model.TrafficLight;
import java.util.Scanner;

public class TrafficLightStateMain {

    public static void main(String[] args) {

        TrafficLight trafficLight = new TrafficLight();

        Thread thread= new Thread(trafficLight);
        thread.start();

        System.out.println("Traffic light is running... Press ENTER to stop.");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        trafficLight.stop();
    }
}
