package com.tss.CreationalDesignPattern.CreationalPatternAssign.model;

public class GamingFactory extends BasePaymentFactory{
    @Override
    public String getDomain() {
        return "Gaming";
    }
}
