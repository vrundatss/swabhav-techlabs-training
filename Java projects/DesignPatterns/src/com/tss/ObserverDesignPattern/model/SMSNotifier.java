package com.tss.BehavioralDesignPattern.ObserverDesignPattern.model;

public class SMSNotifier implements INotifier{

    @Override
    public void sendAlert(String message) {
        System.out.println("\nSMS :  " + message);
    }
}
