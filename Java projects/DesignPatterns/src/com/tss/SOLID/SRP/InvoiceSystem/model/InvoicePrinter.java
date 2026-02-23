package com.tss.SOLID.SRP.InvoiceSystem.model;

public class InvoicePrinter {
    TaxCalculator taxCalculator;

    public InvoicePrinter(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    public void printInvoice() {
        System.out.println("-------------------------------------");
        System.out.println("           Invoice Details           ");
        System.out.println("-------------------------------------");
        System.out.println("Invoice ID        : " + taxCalculator.invoice.getId());
        System.out.println("Description       : " + taxCalculator.invoice.getDescription());
        System.out.println("Amount            : rs." + taxCalculator.invoice.getAmount());
        System.out.println("Tax Percentage    : " + taxCalculator.invoice.getTaxPercentage() + "%");
        System.out.println("Calculated Tax    : rs." + taxCalculator.calculateTax());
        System.out.println("Total Amount      : rs." + (taxCalculator.calculateTax() + taxCalculator.invoice.getAmount()));
        System.out.println("-------------------------------------");
    }

}
