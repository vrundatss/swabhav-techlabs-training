package com.tss.StructuralDesignPattern.DecoratorPattern.model;

public class OilChange extends CarServiceDecorator {

    public OilChange(ICarService carService) {
        super(carService);
    }

    @Override
    public Double getCost() {
        return (500 + super.getCost());
    }
}
