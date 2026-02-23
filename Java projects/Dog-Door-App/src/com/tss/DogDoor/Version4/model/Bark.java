package com.tss.DogDoor.Version4.model;

public class Bark {
    private String sound;

    public Bark(String sound) {
        this.sound = sound;
    }

    public String getSound() {
        return sound;
    }

    @Override
    public boolean equals(Object bark) {
        if (bark instanceof Bark) {
            Bark otherBark = (Bark) bark;
            return this.sound.equalsIgnoreCase(otherBark.sound);
        }
        return false;
    }

    @Override
    public String toString() {
        return sound;
    }
}