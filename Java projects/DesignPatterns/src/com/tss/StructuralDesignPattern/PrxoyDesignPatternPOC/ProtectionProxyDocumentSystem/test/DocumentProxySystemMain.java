package com.tss.StructuralDesignPattern.PrxoyDesignPatternPOC.ProtectionProxyDocumentSystem.test;

import com.tss.StructuralDesignPattern.PrxoyDesignPatternPOC.ProtectionProxyDocumentSystem.model.Document;
import com.tss.StructuralDesignPattern.PrxoyDesignPatternPOC.ProtectionProxyDocumentSystem.model.DocumentProxy;
import com.tss.StructuralDesignPattern.PrxoyDesignPatternPOC.ProtectionProxyDocumentSystem.model.User;

public class DocumentProxySystemMain {
    public static void main(String[] args) {
        User admin = new User("Vrunda", "ADMIN", "admin@123");
        User manager = new User("ABC manager", "MANAGER", "manager@123");
        User employee = new User("Xyz employee", "EMPLOYEE", "emp@123");
        User fake = new User("fake user", "ADMIN", "abc@1234567");

        Document doc1 = new DocumentProxy("EmployeeSalaryDetails.pdf", admin);
        Document doc2 = new DocumentProxy("AnnualReport2026.pdf", manager);
        Document doc3 = new DocumentProxy("ConfidentialStrategy.pdf", employee);
        Document doc4 = new DocumentProxy("SecurityPolicy.pdf", fake);


        System.out.println("\n----- Admin Access -----");
        doc1.display();

        System.out.println("\n----- Manager Access -----");
        doc2.display();

        System.out.println("\n----- Employee Access -----");
        doc3.display();

        System.out.println("\n----- Fake Admin -----");
        doc4.display();

    }
}
