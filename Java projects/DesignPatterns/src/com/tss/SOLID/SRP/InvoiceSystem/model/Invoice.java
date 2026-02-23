package com.tss.SOLID.SRP.InvoiceSystem.model;

public class Invoice {
    private Integer id;
    private String description;
    private Double amount;
    private Double taxPercentage;

    public Invoice(Integer id, String description, Double amount, Double taxPercentage) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.taxPercentage = taxPercentage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(Double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

}
