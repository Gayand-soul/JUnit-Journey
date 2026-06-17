package com.example;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CalculatorTest {

    @Test
    void addsTwoNumbers(){
        Calculator calc = new Calculator();
        assertEquals(5, calc.add(2,3));
    }
    @Test
    void subtractTwoNumbers(){
        Calculator calcSubtract = new Calculator();
        assertEquals(10, calcSubtract.subtract(15, 5));

    }
    @Test
    void multiplyTwoNumbers(){
        Calculator calcMultiply = new Calculator();
        assertEquals(16, calcMultiply.multiply(2, 8));
    }

    @Test
    void divideTwoNumbers(){
        Calculator calcDivide = new Calculator();
        assertEquals(8, calcDivide.divide(48, 6));
    }

}
