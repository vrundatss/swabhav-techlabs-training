package com.tss.CreationalDesignPattern.FactoryPattern.model;

import com.tss.CreationalDesignPattern.FactoryPattern.enums.CarType;

public class CarFactory {
    public  Car getCar(CarType type){
        if (type == null) return null;

        return switch (type){
            case TATA -> new Tata();
            case MARUTI -> new Maruti();
            case HYUNDAI -> new Hyundai();
        };
    }
}
