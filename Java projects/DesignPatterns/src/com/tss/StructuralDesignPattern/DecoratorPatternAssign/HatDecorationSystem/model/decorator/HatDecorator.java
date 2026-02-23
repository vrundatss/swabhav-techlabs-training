package com.tss.StructuralDesignPattern.DecoratorPatternAssign.HatDecorationSystem.model.decorator;

import com.tss.StructuralDesignPattern.DecoratorPatternAssign.HatDecorationSystem.model.types.IHat;

public class HatDecorator implements IHat {
    IHat hat;

    public HatDecorator(IHat hat) {
        this.hat = hat;
    }

    @Override
    public String getName() {
        return hat.getName();
    }

    @Override
    public Double getPrice() {
        return hat.getPrice();
    }

    @Override
    public String getDescription() {
        return hat.getDescription();
    }
}
