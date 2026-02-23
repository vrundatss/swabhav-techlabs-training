package com.tss.StructuralDesignPattern.PrxoyDesignPatternPOC.ProtectionProxyDocumentSystem.model;

public class RealDocument implements Document{

    private  String fileName;

    public RealDocument(String fileName) {
        this.fileName = fileName;
        loadFromServer();
    }

    private void loadFromServer() {
        System.out.println("Loading Confidential Document from secure Server: " + fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying Document : " + fileName);
    }
}
