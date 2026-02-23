package com.tss.Version5.model;

import java.util.*;

public class MandolinSpec extends InstrumentSpec {
    public MandolinSpec(Map<String, Object> properties) {
        super(properties);
    }

    public String getStyle() {
        return (String) getProperty("style");
    }
}