package com.tss.StructuralDesignPattern.AdapterPattern.AdapterStructuralDesignPattern.model.items;

public class Hat {
    private String shortName;
    private String longName;
    private Double basePrice;
    private Double tax;

    public Hat(String shortName, String longName, Double basePrice, Double tax) {
        this.shortName = shortName;
        this.longName = longName;
        this.basePrice = basePrice;
        this.tax = tax;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }
}
