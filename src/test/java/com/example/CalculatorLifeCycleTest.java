package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class CalculatorLifeCycleTest {
    private Calculator calculator;

    @BeforeEach
    void setUp(){
        calculator = new Calculator();
        System.out.println("setup - fresh Calculator created");
    }
    @AfterEach
    void tearDown(){
        calculator = null;
        System.out.println("tearDown - Calculator cleared");
    }
    @Test
    void add_twoPositiveNumbers_returnsCorrectSum(){
        assertEquals(6, calculator.add(2,4));
    }
    @Test
    void add_negativeNumber_returnCorrectResult(){
        assertEquals(1, calculator.add(3, -2));
    }

}
