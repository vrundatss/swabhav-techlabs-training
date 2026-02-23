package com.tss.Version6.model;

public enum InstrumentType {
    GUITAR, MANDOLIN, BANJO, DOBRO, FIDDLE, BASS, ANY;

    @Override
    public String toString() {
        switch(this) {
            case GUITAR: return "Guitar";
            case MANDOLIN: return "Mandolin";
            case BANJO: return "Banjo";
            case DOBRO: return "Dobro";
            case FIDDLE: return "Fiddle";
            case BASS: return "Bass";
            case ANY: return "Any";
            default: return "Unknown";
        }
    }
}