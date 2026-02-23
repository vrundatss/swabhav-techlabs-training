package com.tss.StructuralDesignPattern.CompositeDesignPOC.FileSystem.test;

import com.tss.StructuralDesignPattern.CompositeDesignPOC.FileSystem.model.Folder;

import com.tss.StructuralDesignPattern.CompositeDesignPOC.FileSystem.model.File;

import java.util.Scanner;

public class CompositeFileSystemMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Folder root = new Folder("Root");

        Folder currentFolder = root;

//        FileSystem fileSystem;

        while (true){
            System.out.println("\n======== FILE SYSTEM MENU ========");
            System.out.println("1. Add File");
            System.out.println("2. Add Folder");
            System.out.println("3. List Files/Folders (ls)");
            System.out.println("4. Open All (openAll)");
            System.out.println("5. Change Directory (cd)");
            System.out.println("6. Go Back (..)");
            System.out.println("0. Exit");

            System.out.println("======== Current Folder " + currentFolder.getName() + " ========");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            String name;
            Double size;

            switch (choice){
                case 1 ->{
                    System.out.print("Enter file name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter file size (MB): ");
                    size = scanner.nextDouble();
                    scanner.nextLine();

                    currentFolder.add(new File(name, size));

                    System.out.println("File added to " + currentFolder.getName());
                }

                case 2 -> {
                    System.out.print("Enter folder name: ");
                    String folderName = scanner.nextLine();

                    Folder folder = new Folder(folderName);
                    folder.setParent(currentFolder);

                    currentFolder.add(folder);

                    System.out.println("Folder added to " + currentFolder.getName());
                }

                case 3 -> currentFolder.ls();

                case 4 -> root.openAll("");

                case 5 ->{
                    System.out.print("Enter folder name to navigate: ");
                    String folderName = scanner.nextLine();

                    Folder sub = currentFolder.getSubFolder(folderName);

                    if (sub != null) {
                        currentFolder = sub;
                        System.out.println("Moved into folder: " + currentFolder.getName());
                    } else {
                        System.out.println("Folder not found...");
                    }
                }

                case 6 -> {
                    if (currentFolder.getParent() != null) {
                        currentFolder = currentFolder.getParent();
                        System.out.println("Moved back to folder: " + currentFolder.getName());
                    } else {
                        System.out.println("Already at Root folder.");
                    }
                }
                case 7 -> {
                    System.out.println("Exiting File System...");
                    return;
                }

                default -> System.out.println("Invalid choice...");
            }

        }
    }
}
