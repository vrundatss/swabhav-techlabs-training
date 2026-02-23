package com.tss.CreationalDesignPattern.BuilderPatternPractice.test;

import com.tss.CreationalDesignPattern.BuilderPatternPractice.model.Student;

public class StudentBuilderMain {
    public static void main(String[] args) {

        System.out.println("===== Student Details =====");

        Student student1 = new Student.StudentBuilder(101 , "Vrunda")
                .setEmail("abc@gmail.com")
                .setAge(20)
                .build();

        System.out.println(student1);

        Student student2 = new Student.StudentBuilder(102 , "Tulsi")
                .setEmail("xyz@gmail.com")
                .build();
        System.out.println(student2);


        Student student3 = new Student.StudentBuilder(103 , "Xyz")
                .build();

        System.out.println(student3);


    }
}
