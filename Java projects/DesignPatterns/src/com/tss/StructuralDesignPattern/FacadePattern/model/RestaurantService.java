package com.tss.StructuralDesignPattern.FacadePattern.model;

public class RestaurantService implements HotelService{
    @Override
    public void executeService(Integer roomNumber) {
        System.out.println("Restaurant service: Serving food to " + roomNumber + " room number...");
    }

    public void serveFood() {
        System.out.println("Restaurant: Serving food....");
    }
}
