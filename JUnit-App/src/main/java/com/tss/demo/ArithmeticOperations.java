package com.tss.demo;

public class ArithmeticOperations {


    public int addition(int number1, int number2){
        return number1  + number2;
    }

    int subtraction(int number1 , int number2){
        return number1  - number2;
    }

    public int multiplication(int number1 , int number2){
        return number1  * number2;
    }

    public double division(int number1 , int number2){
        if(number2 == 0)
        {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return (double) number1 / number2;
    }
}
