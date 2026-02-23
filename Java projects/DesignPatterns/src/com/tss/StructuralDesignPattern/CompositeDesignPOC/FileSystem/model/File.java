package com.tss.StructuralDesignPattern.CompositeDesignPOC.FileSystem.model;

public class File implements FileSystem{

    private String name;
    private Double size;

    public File(String name, Double size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void ls() {
        System.out.println("File: " + name + " (" + this.getSize() + "MB)");
    }

    @Override
    public void openAll(String indent) {
        System.out.println(indent + "File: " + name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Double getSize() {
        return this.size;
    }
}
