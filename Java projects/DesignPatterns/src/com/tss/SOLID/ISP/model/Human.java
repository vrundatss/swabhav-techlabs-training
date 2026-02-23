package com.tss.SOLID.ISP.model;

public class Human implements IHuman{
//    String name;
//
//    public Human(String name) {
//        this.name = name;
//    }

    @Override
    public void eat() {
        System.out.println("Human is eating...");
    }

    @Override
    public void rest() {
        System.out.println("Human is resting...");
    }

    @Override
    public void doWork() {
        System.out.println("Human is working...");

    }
}
