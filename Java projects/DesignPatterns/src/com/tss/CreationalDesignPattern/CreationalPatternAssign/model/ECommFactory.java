package com.tss.CreationalDesignPattern.CreationalPatternAssign.model;

public class ECommFactory extends BasePaymentFactory{
    @Override
    public String getDomain() {
        return "E-Commerce";
    }
}
