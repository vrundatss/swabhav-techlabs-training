package com.tss.CreationalDesignPattern.AbstractFactory.model;

public class MarutiFactory implements CarFactory {
    @Override
    public Car getCar() {
        return new Maruti();
    }
}
