package com.tss.DogDoor.Version4.model;

import java.util.Timer;
import java.util.TimerTask;
import java.util.*;

public class DogDoor {
    private boolean open;
    private List<Bark> allowedBarks = new ArrayList<>();

    public DogDoor() {
        this.open = false;
    }

    public void addAllowedBark(Bark bark) {
        allowedBarks.add(bark);
    }

    public List<Bark> getAllowedBarks() {
        return allowedBarks;
    }

    public void openWithAutoClose(int delayMillis) {
        if (open)
            return;
        System.out.println("The dog door opens.");
        open = true;

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                close();
                timer.cancel();
            }
        }, delayMillis);
    }

    public void open() {
        System.out.println("The dog door opens.");
        open = true;
    }

    public void close() {
        if (!open) return;
        System.out.println("The dog door closes.");
        open = false;
    }

    public boolean isOpen() {
        return open;
    }
}