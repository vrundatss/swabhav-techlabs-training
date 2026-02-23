package com.tss.StructuralDesignPattern.FacadePattern.model;

public class Reception {

    public void checkIn(Integer roomNumber){
        new LuggageService().executeService(roomNumber);
        new LuggageService().pickUpLuggage();
        new RestaurantService().executeService(roomNumber);
        new RestaurantService().serveFood();
        new RoomService().executeService(roomNumber);
        new RoomService().roomCleaning();
    }

    public void checkOut(Integer roomNumber){
        new LuggageService().executeService(roomNumber);
        new LuggageService().dropLuggage();
        new RoomService().executeService(roomNumber);
        new RoomService().roomCleaning();
    }
}
