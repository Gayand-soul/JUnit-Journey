package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorWeek1Test {

    //instance variable
    private Calculator calculator;

    @BeforeAll
    static void suiteSetup(){
        System.out.println("----BeforeAll runs before all the tests------");
    }
    @AfterAll
    static void suiteCleanup(){
        System.out.println("-----AfterAll runs after all the tests------");
    }

    @BeforeEach
    void setup(){
        System.out.println(" ..BeforeEach - a fresh new instance creates..");
        calculator = new Calculator();
    }
    @AfterEach
    void tearDown(){
        System.out.println(".... @AfterEach — cleaning up after test ...");
        calculator = null;
    }

    //Assertions
    @Test
    @DisplayName("Add two positive numbers get correct sum:")
    void addTwoPositiveNum_getPositiveSum(){
        assertEquals(11, calculator.add(10,1));
    }
    @Test
    @DisplayName("Add 2 negative numbers get a negative sum")
    void addTwoNegativeNum_negSumResult(){
        assertEquals(-10, calculator.add(-6, -4));
    }
    @Test
    @DisplayName("Multiply two negative numbers get positive product")
    void multiplyTwoNegativeNum_positiveResult(){
        assertEquals(14, calculator.multiply(-2,-7));
    }
    @Test
    @DisplayName("Multiply positive num with negative num get negative product")
    void multiplyPosNum_negNum_negativeProduct(){
        assertTrue(calculator.multiply(-5, 2) < 0);
    }
    @Test
    @DisplayName("divide: 28 divided by 7 returns 4")
    void divide_exactDivision_returnsCorrectQuotient(){
        assertEquals(4, calculator.divide(28, 7));
    }
    @Test
    @DisplayName("multiply: result of 5 times 2 is not zero")
    void multiply_twoPositiveNumbers_resultIsNotZero(){
        assertFalse(calculator.multiply(5, 2) == 0);
    }
    @Test
    @DisplayName("add: result is not equal to wrong expected value")
    void add_twoNumbers_doesNotEqualWrongValue(){
        assertNotEquals(99, calculator.add(2, 3));
    }
    @Test
    @DisplayName("division with zero throws exception")
    void division_withZero (){
        assertThrows(ArithmeticException.class,()->calculator.divide(7,0));
    }
    @Test
    @DisplayName("subtract two negative num gets negative result")
    void subtractNegNumbers_shouldBeNegResult(){
        assertTrue(calculator.subtract(-9,-3) == -6);
    }
    @Test
    @DisplayName("subtract negative num with positive num gets neg result")
    void subtractNegNum_withPosNum_shouldBeNegResult(){
        assertTrue(calculator.subtract(-10, 100) == -110);
    }


}
