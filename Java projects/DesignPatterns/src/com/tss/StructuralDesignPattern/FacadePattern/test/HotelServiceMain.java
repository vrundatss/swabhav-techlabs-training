package com.tss.StructuralDesignPattern.FacadePattern.test;

import com.tss.StructuralDesignPattern.FacadePattern.model.Reception;

public class HotelServiceMain {
    public static void main(String[] args) {
        Reception reception = new Reception();

        System.out.println("Checking In ==>");
        reception.checkIn(101);

        System.out.println("Checking Out ==>");
        reception.checkOut(101);
    }
}
