package com.tss.SOLID.SRP.InvoiceSystem.model;

public class TaxCalculator {
    Invoice invoice;

    public TaxCalculator(Invoice invoice) {
        this.invoice = invoice;
    }

    public Double calculateTax(){
     return invoice.getAmount() * (invoice.getTaxPercentage() / 100);
    }
}
