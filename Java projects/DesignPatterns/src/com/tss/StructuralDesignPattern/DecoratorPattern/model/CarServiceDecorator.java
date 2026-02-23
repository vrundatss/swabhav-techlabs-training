package com.tss.StructuralDesignPattern.DecoratorPattern.model;

public abstract class CarServiceDecorator implements ICarService{
    private ICarService carService;

    public CarServiceDecorator(ICarService carService) {
        this.carService = carService;
    }

    @Override
    public Double getCost() {
        return carService.getCost();
    }
}

