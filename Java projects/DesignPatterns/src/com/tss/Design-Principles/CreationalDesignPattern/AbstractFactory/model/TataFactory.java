package com.tss.CreationalDesignPattern.AbstractFactory.model;

public class TataFactory implements CarFactory{
    @Override
    public Car getCar() {
        return new Tata();
    }
}
