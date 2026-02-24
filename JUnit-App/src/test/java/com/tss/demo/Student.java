package com.tss.demo;

public class Student {
    private StudentService studentService;

    public Student(StudentService studentService){
        this.studentService= studentService;
    }

    public Double calculatePercentage(){
        return ((double) studentService.getTotalMarks() * 100) / studentService.getTotalStudents();
    }
}
