package com.tss.BehavioralDesignPattern.ObserverDesignPattern.model;

public class WhatsappNotifier implements INotifier{
    @Override
    public void sendAlert(String message) {
        System.out.println("\nWhatsapp message :  " + message);
    }
}
