package com.tss.CreationalDesignPattern.AbstractFactory.model;

public class HyundaiFactory implements CarFactory{
    @Override
    public Car getCar() {
        return new Hyundai();
    }
}
