package com.tss.StructuralDesignPattern.PrxoyDesignPatternPOC.VirtualProxyImageSystem.model;

public class ImageProxy implements IImage{
    private String fileName;
    private RealImage realImage;

    public ImageProxy(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
            realImage.loadFromDisk();
        }
        realImage.display();
    }
}
