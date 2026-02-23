package com.tss.DogDoor.Version3.test;

import com.tss.DogDoor.Version3.model.BarkRecognizer;
import com.tss.DogDoor.Version3.model.DogDoor;

// add bark recognizer

public class DogDoorSimulator {
    public static void main(String[] args) {
        DogDoor door = new DogDoor();
        BarkRecognizer barkRecognizer = new BarkRecognizer(door);
//        Remote remote = new Remote(door);

        System.out.println("Fido starts barking...");
        barkRecognizer.recognize("Woof");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nFido’s all done...");
        System.out.println("===> but he’s stuck outside!...");

        System.out.println("Fido starts barking...");
        barkRecognizer.recognize("Woof");

    }
}