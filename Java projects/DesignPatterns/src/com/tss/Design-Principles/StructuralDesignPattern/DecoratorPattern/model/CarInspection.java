package com.tss.StructuralDesignPattern.DecoratorPattern.model;

public class CarInspection implements ICarService{
    @Override
    public Double getCost() {
        return 1000.0;
    }
}
