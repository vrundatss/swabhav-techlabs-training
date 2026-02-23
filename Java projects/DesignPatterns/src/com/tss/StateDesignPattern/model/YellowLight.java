package com.tss.BehavioralDesignPattern.StateDesignPattern.model;

public class YellowLight implements State{
    @Override
    public void handle(TrafficLight context) {
        System.out.println("YELLOW light — Slow down!");
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
        context.setCurrentState(new RedLight());
//        context.request();
    }
}
