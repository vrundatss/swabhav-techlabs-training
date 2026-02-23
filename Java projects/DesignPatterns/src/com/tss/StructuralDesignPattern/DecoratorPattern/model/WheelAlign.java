package com.tss.StructuralDesignPattern.DecoratorPattern.model;

public class WheelAlign extends CarServiceDecorator {
    public WheelAlign(ICarService carService) {
        super(carService);
    }

    @Override
    public Double getCost() {
        return (400 + super.getCost());
    }
}
