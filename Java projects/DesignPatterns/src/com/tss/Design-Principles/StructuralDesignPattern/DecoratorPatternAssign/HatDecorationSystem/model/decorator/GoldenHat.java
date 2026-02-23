package com.tss.StructuralDesignPattern.DecoratorPatternAssign.HatDecorationSystem.model.decorator;

import com.tss.StructuralDesignPattern.DecoratorPatternAssign.HatDecorationSystem.model.types.IHat;

public class GoldenHat extends HatDecorator{

    private static final Double PRICE = 110.0;

    public GoldenHat(IHat hat) {
        super(hat);
    }

    @Override
    public String getName() {
        return hat.getName() + "Golden ";
    }

    @Override
    public Double getPrice() {
        return (hat.getPrice() + PRICE);
    }

    @Override
    public String getDescription() {
        return hat.getDescription() + " and Comes with Golden design.";
    }
}
