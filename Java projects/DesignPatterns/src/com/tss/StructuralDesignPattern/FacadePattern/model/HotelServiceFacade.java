package com.tss.StructuralDesignPattern.FacadePattern.model;

import java.util.ArrayList;
import java.util.List;

public class HotelServiceFacade {
    private List<HotelService> services = new ArrayList<>();

    public void registerService(HotelService service) {
        services.add(service);
    }


}
