package com.tss.Version4.test;

import com.tss.Version4.model.*;

import java.util.*;

public class FindGuitarTester {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        initializeInventory(inventory);

        GuitarSpec whatErinLikes = new GuitarSpec(
                Builder.FENDER, "Stratocastor", Type.ELECTRIC,
                Wood.ALDER, Wood.ALDER, 6);

        List matchingGuitars = inventory.search(whatErinLikes);
        if (!matchingGuitars.isEmpty()) {
            System.out.println("Erin, you might like these guitars:");
            for (Iterator i = matchingGuitars.iterator(); i.hasNext();) {
                Guitar guitar = (Guitar) i.next();
                GuitarSpec spec = guitar.getSpec();
                System.out.println("We have a " + spec.getBuilder() + " " +
                        spec.getModel() + " " + spec.getType() + " guitar:\n" +
                        spec.getBackWood() + " back and sides,\n" +
                        spec.getTopWood() + " top,\n" +
                        spec.getNumStrings() + " strings.\n" +
                        "You can have it for only $" + guitar.getPrice() + "!\n");
            }
        } else {
            System.out.println("Sorry, Erin, we have nothing for you.");
        }
    }

    private static void initializeInventory(Inventory inventory) {
        inventory.addGuitar("V95693", 1499.95,
                new GuitarSpec(Builder.FENDER, "Stratocastor",
                        Type.ELECTRIC, Wood.ALDER, Wood.ALDER, 6));
        inventory.addGuitar("V9512", 1549.95,
                new GuitarSpec(Builder.FENDER, "Stratocastor",
                        Type.ELECTRIC, Wood.ALDER, Wood.ALDER, 6));
    }
}
