package com.tss.BehavioralDesignPattern.CommandDesignPattern.OnOFFSystem.test;

import com.tss.BehavioralDesignPattern.CommandDesignPattern.OnOFFSystem.model.*;

public class OnOffSystemMain {
    public static void main(String[] args) {
        Light light = new Light();

        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(lightOn);
        remote.pressButton();
        remote.pressUndo();

        remote.setCommand(lightOff);
        remote.pressButton();
        remote.pressUndo();

    }
}