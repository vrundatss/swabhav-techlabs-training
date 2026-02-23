package com.tss.StructuralDesignPattern.AdapterPattern.AdapterStructuralDesignPattern.model.items;

public class Biscuit implements Items{
    private String name;
    private Double mrp;

    public Biscuit(String name, Double mrp) {
        this.name = name;
        this.mrp = mrp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMrp(Double mrp) {
        this.mrp = mrp;
    }

    @Override
    public String getItemName() {
        return name;
    }

    @Override
    public Double getItemPrice() {
        return mrp;
    }
}
