package com.tss.demo;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ArithmeticOperationsTest {
    ArithmeticOperations arithmeticOperations;

    @BeforeAll
    static void initAll() {
        System.out.println("Before all tests...");
    }

    @Test
    @BeforeEach
    void init(){
        arithmeticOperations = new ArithmeticOperations();
//        System.out.println("Object initialized...");
    }

    @AfterEach
    void clean() {
        arithmeticOperations = null;
        System.out.println("Cleanup after each test method...");
    }

    @AfterAll
    static void cleanUpAfterAll(){
        System.out.println("After all tests...");
    }

    @Test
    void testAdditionPositiveNumbers() {
        int result = arithmeticOperations.addition(5, 3);
        assertEquals(8, result, "Addition of 5 and 3 should be 8");
    }

    @Test
    void testAdditionWithZero() {
        int result = arithmeticOperations.addition(0, 3);
        assertEquals(3, result, "Addition of 0 and 3 should be 3");
    }

    @Test
    void testSubtractionPositiveNumbers() {
        int result = arithmeticOperations.subtraction(5, 3);
        assertEquals(2, result, "5 - 3 should be 2");
    }

    @Test
    void testSubtractionNegativeNumbers() {
        int result = arithmeticOperations.subtraction(-5, 3);
        assertEquals(-8, result, "-5 - 3 should be -2");
    }

    @Test
    void testMultiplicationPositiveNumbers() {
        int result = arithmeticOperations.multiplication(10, 2);
        assertEquals(20, result, "10 * 2 should be 20");
    }

    @Test
    void testMultiplicationWithZero() {
        assertAll("Multiplication with zero cases",
                () -> assertEquals(0, arithmeticOperations.multiplication(0, 2),
                        "0 * 2 should be 0"),
                () -> assertEquals(0, arithmeticOperations.multiplication(2, 0),
                        "2 * 0 should be 0")
        );
    }
    @Test
    void testDivisionValid() {
        double result = arithmeticOperations.division(20, 2);
        assertEquals(10.0, result, "20 / 2 should be 10");
    }

    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class,
                () -> arithmeticOperations.division(10, 0),
                "Division by zero should throw ArithmeticException");
    }

    @Test
    void testForPositiveSortedOrder(){
        int a = 10 , b = 20 , c = 30;
        assertTrue(a < b && b < c  , "a < b < c in sorted order...");
    }

    @Test
    void testDogInstanceOfAnimal(){
        Animal animal = new Dog();

        assertInstanceOf(Dog.class , animal , "Animal should be a dog instance...");
    }

    @Test
    void testDogAlsoInstanceOfParent(){
        Animal animal = new Dog();

        assertInstanceOf(Animal.class ,animal , "Dog should also be animal...");
    }

//    @Test
//    void testDivisionByZero() {
//        assertDoesNotThrow(ArithmeticException.class ,
//                () -> arithmeticOperations.division(20 , 2));
//    }
}