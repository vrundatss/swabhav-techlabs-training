package com.tss.StructuralDesignPattern.FacadePattern.model;

public class LuggageService implements HotelService{
    @Override
    public void executeService(Integer roomNumber) {
        System.out.println("Luggage service: Carrying luggage to " + roomNumber + " room number...");
    }

    public void pickUpLuggage() {
        System.out.println("Luggage service: Picking up luggage...");
    }

    public void dropLuggage() {
        System.out.println("Luggage service: Dropping luggage...");
    }
}
