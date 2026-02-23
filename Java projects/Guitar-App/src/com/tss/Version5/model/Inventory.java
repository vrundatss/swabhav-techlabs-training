package com.tss.Version5.model;

import java.util.*;

import java.util.*;

public class Inventory {
    private List<Instrument> instruments;

    public Inventory() {
        instruments = new LinkedList<>();
    }

    public void addInstrument(String serialNumber, double price, InstrumentSpec spec) {
        String instrumentType = (String) spec.getProperty("instrumentType");
        if (instrumentType.equalsIgnoreCase("Guitar")) {
            instruments.add(new Guitar(serialNumber, price, (GuitarSpec) spec));
        } else if (instrumentType.equalsIgnoreCase("Mandolin")) {
            instruments.add(new Mandolin(serialNumber, price, (MandolinSpec) spec));
        }
    }

    public List<Instrument> search(InstrumentSpec searchSpec) {
        List<Instrument> matchingInstruments = new LinkedList<>();
        for (Instrument instrument : instruments) {
            if (instrument.getSpec().matches(searchSpec))
                matchingInstruments.add(instrument);
        }
        return matchingInstruments;
    }
}