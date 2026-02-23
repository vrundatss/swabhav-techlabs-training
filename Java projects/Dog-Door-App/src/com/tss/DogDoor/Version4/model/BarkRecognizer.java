package com.tss.DogDoor.Version4.model;

import java.util.Timer;
import java.util.TimerTask;

import java.util.*;

public class BarkRecognizer {
    private DogDoor door;

    public BarkRecognizer(DogDoor door) {
        this.door = door;
    }

    public boolean recognize(Bark bark) {
        System.out.println("BarkRecognizer: Heard a '" + bark.getSound() + "'");
        for (Bark allowedBark : door.getAllowedBarks()) {
            if (bark.equals(allowedBark)) {
                System.out.println("Bark recognized! Opening the door...");

                door.openWithAutoClose(5000);
                return true;
            }
        }
        System.out.println("This bark is not recognized. Door remains closed.");
        return false;
    }
}