package com.tss.DogDoor.Version4.model;

import java.util.Timer;
import java.util.TimerTask;

import java.util.*;

public class Remote {
    private DogDoor door;

    public Remote(DogDoor door) {
        this.door = door;
    }

    public void pressButton() {
        System.out.println("Pressing the remote control button...");
        if (door.isOpen()) {
            door.close();
        } else {
            door.openWithAutoClose(5000);
        }
    }
}