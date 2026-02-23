package com.tss.DogDoor.Version2.test;

import com.tss.DogDoor.Version2.model.DogDoor;
import com.tss.DogDoor.Version2.model.Remote;

// automatically close the door after 10 sec

public class DogDoorSimulator {
    public static void main(String[] args) {
        DogDoor door = new DogDoor();
        Remote remote = new Remote(door);

        System.out.println("\nFido barks to go outside...");
        remote.pressButton();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nFido’s all done...");
        System.out.println("===> but he’s stuck outside!...");

        System.out.println("\nFido barks to come back inside...");
        System.out.println("--->so Gina grabs the remote control...");

        remote.pressButton();
    }
}