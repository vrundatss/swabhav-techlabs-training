package com.tss.DogDoor.Version3.model;

public class BarkRecognizer {
    private DogDoor door;
    public BarkRecognizer(DogDoor door) {
        this.door = door;
    }
    public void recognize(String bark) {
        System.out.println("  Bark Recognizer: Heard a  " + bark );
        door.open();
    }

}