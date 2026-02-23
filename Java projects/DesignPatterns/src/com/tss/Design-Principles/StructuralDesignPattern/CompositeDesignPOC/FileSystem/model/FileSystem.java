package com.tss.StructuralDesignPattern.CompositeDesignPOC.FileSystem.model;

public interface FileSystem {
    void ls();
    void openAll(String indent);

    String getName();
//    void cd(String folderName);
}
