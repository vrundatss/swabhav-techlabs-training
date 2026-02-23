package com.tss.SOLID.SRP.InvoiceSystem.test;

import com.tss.SOLID.SRP.InvoiceSystem.model.Invoice;
import com.tss.SOLID.SRP.InvoiceSystem.model.InvoicePrinter;
import com.tss.SOLID.SRP.InvoiceSystem.model.TaxCalculator;

public class InvoiceMain {
    public static void main(String[] args) {

        Invoice invoice1 = new Invoice(101 , "Detail..." , 10000.0 , 6.0);
//        Invoice invoice2 = new Invoice(101 , "Data" , 10000.0 , 10.0);

        TaxCalculator taxCalculator = new TaxCalculator(invoice1);


        taxCalculator.calculateTax();

        InvoicePrinter invoicePrinter = new InvoicePrinter(taxCalculator);

        invoicePrinter.printInvoice();



    }
}
