package com.tss.Version1.test;

import com.tss.Version1.model.Guitar;
import com.tss.Version1.model.Inventory;

public class FindGuitarTester {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        initializeInventory(inventory);

        Guitar whatErinLikes = new Guitar("", 0, "Fender", "Stratocastor",
                "electric", "Alder", "Alder");

        Guitar guitar = inventory.search(whatErinLikes);
        if (guitar != null) {
            System.out.println("Erin, you might like this " +
                    guitar.getBuilder() + " " + guitar.getModel() + " " +
                    guitar.getType() + " guitar:\n" +
                    guitar.getBackWood() + " back and sides,\n" +
                    guitar.getTopWood() + " top.\nYou can have it for only $" +
                    guitar.getPrice() + "!");
        } else {
            System.out.println("Sorry, Erin, we have nothing for you.");
        }
    }

    private static void initializeInventory(Inventory inventory) {
        inventory.addGuitar("V95693", 1499.95, "Fender", "Stratocastor",
                "electric", "Alder", "Alder");
        inventory.addGuitar("V9512", 1549.95, "Fender", "Stratocastor",
                "electric", "Alder", "Alder");
    }
}
