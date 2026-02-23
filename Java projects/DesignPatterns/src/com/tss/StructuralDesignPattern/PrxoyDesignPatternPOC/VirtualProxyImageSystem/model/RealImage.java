package com.tss.StructuralDesignPattern.PrxoyDesignPatternPOC.VirtualProxyImageSystem.model;

import javax.swing.*;
import java.awt.*;

public class RealImage implements IImage{

    String fileName;
    private ImageIcon imageIcon;
    private boolean loaded = false;

    public RealImage(String fileName) {
        this.fileName = fileName;
//        loadFromDisk();
    }

    @Override
    public void display() {
//        loadFromDisk();
        System.out.println("\nDisplaying image: " + fileName);

        // Create JFrame to show the image
//        JFrame frame = new JFrame("Virtual Proxy Image Viewer");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a new window each time display() is called
        JFrame frame = new JFrame("Virtual Proxy Image Viewer");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ✅ keeps app running after closing
        frame.setLayout(new BorderLayout());

        // Add image to label
        JLabel label = new JLabel(imageIcon);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        // Optional: resize image to fit window
        Image img = imageIcon.getImage();
        Image scaledImg = ((Image) img).getScaledInstance(600, 400, java.awt.Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaledImg));

        frame.add(label);
        frame.setSize(700, 500);
        frame.setVisible(true);    }

    public void loadFromDisk() {
        System.out.println("\nLoading image from disk: " + fileName);
        imageIcon = new ImageIcon(fileName);
    }
}
