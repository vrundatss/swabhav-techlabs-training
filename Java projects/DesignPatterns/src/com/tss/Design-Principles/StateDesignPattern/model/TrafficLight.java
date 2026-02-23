package com.tss.BehavioralDesignPattern.StateDesignPattern.model;

public class TrafficLight implements Runnable{
    private State currentState;

    private boolean running = true;

    public TrafficLight(){
        this.currentState = new RedLight();
    }

    public void setCurrentState(State State) {
        this.currentState = State;
    }

//    public void request() {
//        currentState.handle(this);
//    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            currentState.handle(this);
        }
        System.out.println("\nTraffic Light system stopped!!!...");
    }
}
