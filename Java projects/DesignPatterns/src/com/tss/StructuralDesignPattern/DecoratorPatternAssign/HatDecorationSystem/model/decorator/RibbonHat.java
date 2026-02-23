package com.tss.StructuralDesignPattern.DecoratorPatternAssign.HatDecorationSystem.model.decorator;

import com.tss.StructuralDesignPattern.DecoratorPatternAssign.HatDecorationSystem.model.types.IHat;

public class RibbonHat extends HatDecorator{

    private static final Double PRICE = 60.0;

    public RibbonHat(IHat hat) {
        super(hat);
    }

    @Override
    public String getName() {
        return hat.getName() + " Ribbon ";
    }

    @Override
    public Double getPrice() {
        return hat.getPrice() + PRICE;
    }

    @Override
    public String getDescription() {
        return hat.getDescription() + " and comes with beautiful Ribbon ";
    }
}
