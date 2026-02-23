package com.tss.SOLID.ISP.model;

public class Robot implements IRobot{
    @Override
    public void charge() {
        System.out.println("Robot is working...");
    }

    @Override
    public void doWork() {
        System.out.println("Robot is working...");

    }
}
