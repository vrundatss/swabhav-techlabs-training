package com.tss.Version6.model;

import java.util.HashMap;
import java.util.Map;


import java.util.*;
import java.util.*;

public class InstrumentSpec {
    private Map<String, Object> properties;

    public InstrumentSpec(Map<String, Object> properties) {
        if (properties == null)
            this.properties = new HashMap<>();
        else
            this.properties = new HashMap<>(properties);
    }

    public Object getProperty(String propertyName) {
        return properties.get(propertyName);
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public boolean matches(InstrumentSpec otherSpec) {
        for (String propertyName : otherSpec.getProperties().keySet()) {
            Object value = otherSpec.getProperty(propertyName);
            if (value != null && !value.equals(properties.get(propertyName)))
                return false;
        }
        return true;
    }
}
