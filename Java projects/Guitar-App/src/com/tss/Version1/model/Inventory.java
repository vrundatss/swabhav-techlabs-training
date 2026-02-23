package com.tss.Version1.model;
import java.util.*;

public class Inventory {
    private List guitars;

    public Inventory() {
        guitars = new LinkedList();
    }

    public void addGuitar(String serialNumber, double price, String builder,
                          String model, String type, String backWood, String topWood) {
        Guitar guitar = new Guitar(serialNumber, price, builder, model, type, backWood, topWood);
        guitars.add(guitar);
    }

    public Guitar getGuitar(String serialNumber) {
        for (Iterator i = guitars.iterator(); i.hasNext();) {
            Guitar guitar = (Guitar) i.next();
            if (guitar.getSerialNumber().equals(serialNumber))
                return guitar;
        }
        return null;
    }

    public Guitar search(Guitar searchGuitar) {
        for (Iterator i = guitars.iterator(); i.hasNext();) {
            Guitar guitar = (Guitar) i.next();
            if (searchGuitar.getBuilder() != null && !searchGuitar.getBuilder().equals(guitar.getBuilder()))
                continue;
            if (searchGuitar.getModel() != null && !searchGuitar.getModel().equals(guitar.getModel()))
                continue;
            if (searchGuitar.getType() != null && !searchGuitar.getType().equals(guitar.getType()))
                continue;
            if (searchGuitar.getBackWood() != null && !searchGuitar.getBackWood().equals(guitar.getBackWood()))
                continue;
            if (searchGuitar.getTopWood() != null && !searchGuitar.getTopWood().equals(guitar.getTopWood()))
                continue;
            return guitar;
        }
        return null;
    }
}
