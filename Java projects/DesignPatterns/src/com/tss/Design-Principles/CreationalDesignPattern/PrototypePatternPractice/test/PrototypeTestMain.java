package com.tss.CreationalDesignPattern.PrototypePatternPractice.test;

import com.tss.CreationalDesignPattern.PrototypePatternPractice.model.Employee;

public class PrototypeTestMain {
    public static void main(String[] args) {
        Employee employee1 = new Employee(101 , "Vrunda", "Software Developer");
        Employee employee2 = (Employee) employee1.clone();

        System.out.println(employee1);
        System.out.println(employee2);
    }
}
