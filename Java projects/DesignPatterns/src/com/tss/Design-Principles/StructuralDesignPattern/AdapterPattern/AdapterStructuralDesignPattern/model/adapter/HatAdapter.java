package com.tss.StructuralDesignPattern.AdapterPattern.AdapterStructuralDesignPattern.model.adapter;

import com.tss.StructuralDesignPattern.AdapterPattern.AdapterStructuralDesignPattern.model.items.Hat;
import com.tss.StructuralDesignPattern.AdapterPattern.AdapterStructuralDesignPattern.model.items.Items;

public class HatAdapter implements Items {

    private Hat hat;

    public HatAdapter(Hat hat) {
        this.hat = hat;
    }

    @Override
    public String getItemName() {
        return hat.getShortName() + " " + hat.getLongName();
    }

    @Override
    public Double getItemPrice() {
        return hat.getBasePrice() + (hat.getBasePrice() * hat.getTax() / 100);
    }
}
