package com.tss.Version5.model;


import java.util.*;

public class GuitarSpec extends InstrumentSpec {
    public GuitarSpec(Map<String, Object> properties) {
        super(properties);
    }

    public int getNumStrings() {
        Object value = getProperty("numStrings");
        if (value == null) return 0;
        return (Integer) value;
    }
}