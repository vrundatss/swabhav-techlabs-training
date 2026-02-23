package com.tss.CreationalDesignPattern.PrototypePatternPractice.model;

public class Employee implements Prototype{

    private Integer id;
    private String name;
    private String department;

    public Employee(Integer id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    @Override
    public Prototype clone() {
        return new Employee(this.id , this.name , this.department);
    }

    @Override
    public String toString() {
        return
                "Id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'';
    }
}
