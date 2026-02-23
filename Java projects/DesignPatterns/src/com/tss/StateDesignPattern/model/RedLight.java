package com.tss.BehavioralDesignPattern.StateDesignPattern.model;

public class RedLight implements State{
    @Override
    public void handle(TrafficLight context) {
        System.out.println("RED light — Stop!");
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
        context.setCurrentState(new GreenLight());
    }
}
