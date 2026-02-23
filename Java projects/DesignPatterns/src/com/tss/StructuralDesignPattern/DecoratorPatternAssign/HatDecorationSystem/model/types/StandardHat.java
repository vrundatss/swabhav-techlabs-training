package com.tss.StructuralDesignPattern.DecoratorPatternAssign.HatDecorationSystem.model.types;

public class StandardHat implements IHat {
    private static final Double PRICE = 250.50;

    @Override
    public String getName() {
        return "Standard ";
    }

    @Override
    public Double getPrice() {
        return PRICE;
    }

    @Override
    public String getDescription() {
        return "A standard hat for casual wear.";
    }
}

