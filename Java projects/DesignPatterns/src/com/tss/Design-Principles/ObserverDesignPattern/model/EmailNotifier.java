package com.tss.BehavioralDesignPattern.ObserverDesignPattern.model;

public class EmailNotifier implements INotifier{
    @Override
    public void sendAlert(String message) {
        System.out.println("\nEmail :  " + message);

    }
}
