package com.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



public class CalculatorWeek2Test {

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

    //@Nested 3 inner classes
    @Nested
    @DisplayName("Addition Tests:")
    class AdditionTests {
        @Test
        @DisplayName("Add two positive numbers get correct sum:")
        void add_posNumbers_returnSum(){
            assertEquals(5, calculator.add(4,1));
        }
        @Test
        @DisplayName("Add 2 negative numbers get negative sum")
        void add_negNumbers_returnNegSum(){
            assertEquals(-8, calculator.add(-3,-5));
        }
        @Test
        @DisplayName("Add big negative number and small positiv number get negative sum")
        void add_negNumber_withPosNum_getNegSum(){
            assertEquals(-3, calculator.add(-8,5));
        }
        @Test
        @DisplayName("Add big positive number to a small negative number get positive sum")
        void add_posNum_withSmallNegNum_getPosSum(){
            assertEquals(3, calculator.add(10,-7));
        }
    }

    @Nested
    @DisplayName("Division Tests:")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class DivisionTests {
        @Test
        @Order(1)
        @DisplayName("Division: Happy Path")
        void divideBigNum_withSmallNum_getResult() {
            assertEquals(5, calculator.divide(50, 10));
        }
        @Test
        @Order(3)
        @DisplayName("Division: Positive number with negative number get neg result.")
        void dividePosNum_withNegNum_getNegResult(){
            assertEquals(-5, calculator.divide(-25,5));
        }
        @Test
        @Order(2)
        @DisplayName("Division: Two negative numbers get positive result")
        void divideNegNum_withNegNum_getPosResult(){
            assertEquals(5, calculator.divide(-25,-5));
        }
        @Test
        @Order(4)
        @DisplayName("Division with zero")
        void divideNum_withZero_shouldThrowWithMessage(){
            ArithmeticException exception = assertThrows(ArithmeticException.class, ()->calculator.divide(10,0));
            assertEquals("Divide by zero", exception.getMessage());
        }
        @Test
        @Disabled("Method should reconsider moving to Addition tests")
        void addTwoRandomNum_getPosResult(){
            // Remember: this method should be moved to Addition Tests...
        }
    }

    @Nested
    @DisplayName("Timeout Tests with assertInstanceOf and assertIterables")
    class TimeoutTests {

        @Test
        @DisplayName("Addition results shows within 500 ms")
        void addTwoNum_shouldReturnResult_within500ms (){

            //Arrange
            int a= 10, b= 5;
            //Act
            int result = assertTimeout(Duration.ofMillis(500), ()-> calculator.add(a, b));

            //Assert
            assertEquals(15, result);

        }
        @Test
        @DisplayName("Subtract results shows within 5sec")
        void subtractTwoNum_shouldReturnResult_within5sec(){
            //Arrange
            int x=7, y=2;
            //Act
            int result = assertTimeout(Duration.ofSeconds(5), ()-> calculator.subtract(x, y));
            //Assert
            assertEquals(5, result);
        }
        @Test
        @DisplayName("To verify an object with AssertInstance Of")
        void result_shouldBeAnInteger(){
            Object result = calculator.add(2,3);
            assertInstanceOf(Integer.class, result);
        }
        @Test
        @DisplayName("Different collectiontypes same content")
        void checkTwolists_sameContent_getResult(){
            List<Integer> expected = List.of(2,4,6);
            LinkedList<Integer> actual = new LinkedList<>(List.of(2,4,6));
            assertIterableEquals(expected, actual);
        }
    }
    @Test
    @DisplayName(" AssertAll with multiplications")
    void multiplyNumbers_getCorrectResult (){
        assertAll("Multiplication results",
                ()-> assertEquals(5, calculator.multiply(5,1)),
                ()-> assertEquals(-50, calculator.multiply(-10, 5)),
                ()-> assertEquals(0, calculator.multiply(10,0)),
                ()-> assertEquals(23, calculator.multiply(-23, -1))
        );
    }
}
