package com.tss.StructuralDesignPattern.CompositeDesignPOC.FileSystem.model;

import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystem{

    private String name;
    private List<FileSystem> children = new ArrayList<>();
    private Folder parent;

    public Folder(String name) {
        this.name = name;
    }
    public void setParent(Folder parent) {
        this.parent = parent;
    }

    public Folder getParent() {
        return parent;
    }


    public void add(FileSystem component) {
        if (component instanceof Folder folder) {
            folder.setParent(this);
        }
        children.add(component);
    }

    public void remove(FileSystem component) {
        children.remove(component);
    }

    @Override
    public void ls() {
        System.out.println("\nFolder: " + name);
        if (children.isEmpty()) {
            System.out.println("  (empty)");
        } else {
            for (FileSystem component : children) {
                component.ls();
            }
        }
    }

    @Override
    public void openAll(String indent) {
        System.out.println(indent + "Folder: " + name);
        for (FileSystem c : children) {
            c.openAll(indent + "   ");
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void cd(String folderName) {
        System.out.println("You are already in folder: " + name);
    }

    public Folder getSubFolder(String folderName) {
        for (FileSystem component : children) {
            if (component instanceof Folder folder && folder.name.equalsIgnoreCase(folderName)) {
                return folder;
            }
        }
        return null;
    }
}
