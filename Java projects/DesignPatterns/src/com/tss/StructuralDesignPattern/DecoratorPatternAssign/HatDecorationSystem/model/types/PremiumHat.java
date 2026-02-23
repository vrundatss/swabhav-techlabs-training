package com.tss.StructuralDesignPattern.DecoratorPatternAssign.HatDecorationSystem.model.types;

public class PremiumHat implements IHat{

    private static final Double PRICE = 450.50;

    @Override
    public String getName() {
        return "Premium ";
    }

    @Override
    public Double getPrice() {
        return PRICE;
    }

    @Override
    public String getDescription() {
        return "A luxury hat made from premium materials.";
    }

}
