package com.tss.CreationalDesignPattern.FactoryPattern.model;

public class Hyundai implements Car{
    @Override
    public void drive() {
        System.out.println("Driving Hyundai Car...");
    }
}
