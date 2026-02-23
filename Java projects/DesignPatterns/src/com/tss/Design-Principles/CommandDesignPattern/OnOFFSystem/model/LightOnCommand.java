package com.tss.BehavioralDesignPattern.CommandDesignPattern.OnOFFSystem.model;

public class LightOnCommand implements Command{
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
     light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }
}
