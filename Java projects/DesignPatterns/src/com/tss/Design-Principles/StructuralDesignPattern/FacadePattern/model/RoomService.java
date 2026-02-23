package com.tss.StructuralDesignPattern.FacadePattern.model;

public class RoomService implements HotelService{
    @Override
    public void executeService(Integer roomNumber) {
        System.out.println("Room service: Cleaning " + roomNumber + " room number...");
    }

    public void roomCleaning(){
        System.out.println("Room service: Cleaning room....");
    }
}
