package com.tss.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;


public class StudentTest {
    private  Student student;
    private  StudentService studentService;

    @BeforeEach
    void init(){
        studentService = Mockito.mock(StudentService.class);
        student = new Student(studentService);
    }

    @Test
    void calculatePercentage(){
        Mockito.when(studentService.getTotalMarks()).thenReturn(400);
        Mockito.when(studentService.getTotalStudents()).thenReturn(500);

        assertEquals(80 , student.calculatePercentage());
    }
}
