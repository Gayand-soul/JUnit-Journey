package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorAssertionsTest {
    private Calculator calculator;

    @BeforeEach
    void setup(){
        calculator = new Calculator();
        System.out.println("setup - fresh Calculator created");
    }
    @AfterEach
    void teardown(){
        calculator = null;
        System.out.println("tearDown - Calculator cleared");
    }
    //assertEquals
    @Test
    void addTwoNum_getCorrectSum(){
        assertEquals(10, calculator.add(6, 4));
    }
    @Test
    void subtractNum_getCorrectResult(){
        assertEquals(9, calculator.subtract(10,1));
    }
    //assertNotEquals
    @Test
    void add_returnsWrongValue_fails(){
      assertNotEquals(99, calculator.add(2,3));
    }
    //assertTrue
    @Test
    void result_shouldBePositive(){
        assertTrue(calculator.add(2,3) > 0);
    }
    //assertFalse
    @Test
    void result_shouldBeNegative(){
        assertFalse(calculator.subtract(2,5) >= 0);
    }
    //assertNotNull
    @Test
    void calculator_isNotNull_afterSetup(){
        assertNotNull(calculator);
    }
    //assertNull
    @Test
    void nullString_isNull(){
        String result = null;
        assertNull(result);
    }
    //assertThrows
    @Test
    void divide_byZero_throwException(){
        assertThrows(ArithmeticException.class, () -> calculator.divide(7,0));
    }
    //assertAll
    @Test
    void add_multipleChecks_allPassTogether(){
        assertAll(
                ()-> assertEquals(5, calculator.add(4,1)),
                ()-> assertEquals(6, calculator.subtract(7,1)),
                ()-> assertEquals(0, calculator.multiply(0,0)),
                ()-> assertEquals(3, calculator.divide(9,3))
        );
    }

}
