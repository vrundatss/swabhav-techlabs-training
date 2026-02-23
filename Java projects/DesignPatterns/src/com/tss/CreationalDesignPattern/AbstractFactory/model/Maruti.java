package com.tss.CreationalDesignPattern.AbstractFactory.model;

public class Maruti implements Car {
    @Override
    public void drive() {
        System.out.println("driving Maruti car...");
    }
}
