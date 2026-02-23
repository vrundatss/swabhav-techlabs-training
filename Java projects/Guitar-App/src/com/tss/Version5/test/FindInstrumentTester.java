package com.tss.Version5.test;

import com.tss.Version5.model.*;

import java.util.*;

public class FindInstrumentTester {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        initializeInventory(inventory);

        Map<String, Object> guitarProps = new HashMap<>();
        guitarProps.put("instrumentType", "Guitar");
        guitarProps.put("builder", Builder.FENDER);
        guitarProps.put("model", "Stratocastor");
        guitarProps.put("type", Type.ELECTRIC);
        guitarProps.put("backWood", Wood.ALDER);
        guitarProps.put("topWood", Wood.ALDER);
        guitarProps.put("numStrings", 6);
        GuitarSpec whatErinLikes = new GuitarSpec(guitarProps);

        List<Instrument> matchingInstruments = inventory.search(whatErinLikes);
        if (!matchingInstruments.isEmpty()) {
            System.out.println("Erin, you might like these guitars:");
            for (Instrument instrument : matchingInstruments) {
                GuitarSpec spec = (GuitarSpec) instrument.getSpec();
                System.out.println("We have a " + spec.getProperty("builder") + " " +
                        spec.getProperty("model") + " " +
                        spec.getProperty("type") + " " +
                        spec.getProperty("instrumentType") + ":\n" +
                        spec.getProperty("backWood") + " back and sides,\n" +
                        spec.getProperty("topWood") + " top,\n" +
                        spec.getNumStrings() + " strings.\n" +
                        "Price: $" + instrument.getPrice() + "\n");
            }
        }

        Map<String, Object> mandolinProps = new HashMap<>();
        mandolinProps.put("instrumentType", "Mandolin");
        mandolinProps.put("builder", Builder.GIBSON);
        mandolinProps.put("style", "A");
        MandolinSpec mandolinSearch = new MandolinSpec(mandolinProps);

        List<Instrument> foundMandolins = inventory.search(mandolinSearch);
        if (!foundMandolins.isEmpty()) {
            System.out.println("Matching mandolins:");
            for (Instrument instrument : foundMandolins) {
                MandolinSpec spec = (MandolinSpec) instrument.getSpec();
                System.out.println("We have a " + spec.getProperty("builder") + " mandolin of style " +
                        spec.getStyle() + ". Price: $" + instrument.getPrice());
            }
        }
    }

    private static void initializeInventory(Inventory inventory) {

        Map<String, Object> props = new HashMap<>();
        props.put("instrumentType", "Guitar");
        props.put("builder", Builder.FENDER);
        props.put("model", "Stratocastor");
        props.put("type", Type.ELECTRIC);
        props.put("backWood", Wood.ALDER);
        props.put("topWood", Wood.ALDER);
        props.put("numStrings", 6);

        inventory.addInstrument("V95693", 1499.95, new GuitarSpec(props));


        Map<String, Object> mandolinProps = new HashMap<>();
        mandolinProps.put("instrumentType", "Mandolin");
        mandolinProps.put("builder", Builder.GIBSON);
        mandolinProps.put("style", "A");

        inventory.addInstrument("M1234", 1699.99, new MandolinSpec(mandolinProps));
    }
}