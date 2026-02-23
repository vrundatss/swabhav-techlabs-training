package com.tss.DogDoor.Version4.test;

import com.tss.DogDoor.Version4.model.BarkRecognizer;
import com.tss.DogDoor.Version4.model.DogDoor;
import com.tss.DogDoor.Version4.model.Bark;
import com.tss.DogDoor.Version4.model.Remote;

// added bark recognizer and Bark class for owner's dog
// added List of allowed barks...
// added DRY principle using centralized openWithAutoClose(millis) method

public class DogDoorSimulator {
    public static void main(String[] args) {
        DogDoor door = new DogDoor();
        BarkRecognizer recognizer = new BarkRecognizer(door);
        Remote remote = new Remote(door);

        // these all are list of bruce's barks...
        door.addAllowedBark(new Bark("rowlf"));
        door.addAllowedBark(new Bark("rooowlf"));
        door.addAllowedBark(new Bark("rawlf"));
        door.addAllowedBark(new Bark("woof"));

        System.out.println("\nFido starts barking to go outside...");
        boolean recognized = recognizer.recognize(new Bark("rowlf"));

        if (!recognized) {
            System.out.println("Recognizer failed! Using remote instead...");
            remote.pressButton();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nFido’s all done...\n ===> barks to come back inside...");
        recognized = recognizer.recognize(new Bark("rooowlf"));

        // if recognizer fails, ==> remote will work...
        if (!recognized) {
            System.out.println("Recognizer failed again! Using remote...");
            remote.pressButton();
        }

        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nA random dog nearby barks...");
        recognizer.recognize(new Bark("yip"));
    }
}