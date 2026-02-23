package com.tss.BehavioralDesignPattern.StateDesignPattern.model;

public class GreenLight implements State{
    @Override
    public void handle(TrafficLight context) {
        System.out.println("GREEN light — Go!");
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
        context.setCurrentState(new YellowLight());
    }
}
