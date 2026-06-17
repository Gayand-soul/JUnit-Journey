package com.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LifeCycleTest {
    //This instance is recreated fresh before EACH test(default PER_METHOD)
    //Watch how counter and status reset automatically between tests!

    //instance variable
    private LifecycleTestCases lifecycle;

    @BeforeAll
    static void suiteSetup(){
        System.out.println("=== @BeforeAll  — runs ONCE before all tests ===");
    }
    @AfterAll
    static void suiteCleanup(){
        System.out.println("=== @AfterAll   — runs ONCE after all tests ===");
    }
    @BeforeEach
    void setup(){
        System.out.println("--- @BeforeEach — creating fresh TestLifecycle instance ---");
        lifecycle = new LifecycleTestCases();
    }
    @AfterEach
    void tearDown(){
        System.out.println("--- @AfterEach  — cleaning up after test ---");
        lifecycle.clearLog();
    }

    //Block 1: Default status behaviour
    @Test
    @Order(1)
    @DisplayName("Initial status should be NEW")
    void testInitialStatus(){
        System.out.println("Running: testInitialStatus");
        assertEquals("NEW", lifecycle.getStatus());
        assertFalse(lifecycle.isActive());
    }

    @Test
    @Order(2)
    @DisplayName("activate() should change status to ACTIVE")
    void testActivate(){
        System.out.println("Running: testActivate");
        lifecycle.activate();
        assertEquals("ACTIVE", lifecycle.getStatus());
        assertTrue(lifecycle.isActive());
    }

    @Test
    @Order(3)
    @DisplayName("deactivate() should change status to INACTIVE")
    void testDeactivate(){
        System.out.println("Running: testDeactivate");
        lifecycle.activate();
        lifecycle.deactivate();
        assertEquals("INACTIVE", lifecycle.getStatus());
        assertFalse(lifecycle.isActive());
    }

    @Test
    @Order(4)
    @DisplayName("deactivate() on a NEW instance should have no effect")
    void testDeactivateOnNewInstance(){
        System.out.println("Running: testDeactivateOnNewInstance");
        lifecycle.deactivate();
        assertEquals("NEW", lifecycle.getStatus());
    }

    // Block 2: Event log behaviour
    @Test
    @Order(5)
    @DisplayName("logEvent() should add an event to the log")
    void testLogEvent(){
        System.out.println("Running: testlogEvent");
        lifecycle.logEvent("Started");
        assertEquals(1, lifecycle.getEventCount());
        assertTrue(lifecycle.getEventLog().contains("Started"));
    }
    @Test
    @Order(6)
    @DisplayName("logEvent() with null should throw IllegalArgumentException")
    void testLogEventNull(){
        System.out.println("Runnning: testLogEventNull");
        assertThrows(IllegalArgumentException.class, ()-> lifecycle.logEvent(null));
    }
    @Test
    @Order(7)
    @DisplayName("logEvent() with blank string should throw IllegalArgumentException")
    void testLogEventBlank(){
        System.out.println("Running: testLogEventBlank");
        assertThrows(IllegalArgumentException.class, ()-> lifecycle.logEvent(" "));
    }
    @Test
    @Order(8)
    @DisplayName("clearLog() should empty the event log")
    void testClearLog(){
        System.out.println("Running: testClearLog");
        lifecycle.logEvent("AAAA");
        lifecycle.logEvent("HOW, WHY");
        lifecycle.clearLog();
        assertEquals(0,lifecycle.getEventCount());
    }

    // Block 3: Counter behaviour — proving fresh instance per test
    @Test
    @Order(9)
    @DisplayName("Counter should start at 0 on every test (proves fresh instance)")
    void testCounterStartsAtZero(){
        System.out.println("Running: testCounterStartsAtZero");

        //No matter what other tests did, counter is always 0 here
        assertEquals(0, lifecycle.getCounter());
    }
    @Test
    @Order(10)
    @DisplayName("increment() should increase counter by 1")
    void testIncrement(){
        System.out.println("Running: testIncrement");
        lifecycle.increment();
        lifecycle.increment();
        assertEquals(2, lifecycle.getCounter());
    }
    @Test
    @Order(11)
    @DisplayName("increment(amount) should increase counter by given amount")
    void testIncrementByAmount(){
        System.out.println("Running:testIncrementByAmount ");
        lifecycle.increment(5);
        assertEquals(5, lifecycle.getCounter());
    }
    @Test
    @Order(12)
    @DisplayName(" increment() with negative amount should throw IllegalArgumentException")
    void testIncrementNegative() {
        System.out.println(" Running: testIncrementNegative()");
        assertThrows(IllegalArgumentException.class, ()-> lifecycle.increment(-2));
    }
    @Test
    @Order(13)
    @DisplayName("Reset should bring counter back to 0")
    void testReset(){
        System.out.println("Running: testReset");
        lifecycle.increment(12);
        lifecycle.reset();
        assertEquals(0, lifecycle.getCounter());
    }

    // Block 4: Nested class — demonstrates lifecycle chaining
    @Nested
    @DisplayName("When lifecycle is ACTIVE")
    class WhenActive {

        @BeforeEach
        void activate(){
            System.out.println("  >>> Inner @BeforeEach — activating lifecycle");
            lifecycle.activate(); //uses the outer lifecycle instance.

        }
        @AfterEach
        void report(){
            System.out.println("   >>> Inner @AfterEach — status was: " + lifecycle.getStatus());
        }
        @Test
        @DisplayName(" isActive() should return true")
        void testIsActive(){
            System.out.println("      Running: nested testIsActive");
            assertTrue(lifecycle.isActive());
        }
        @Test
        @DisplayName("deactivate() should move status to INACTIVE")
        void testDeactivateFromActive(){
            System.out.println("      Running: nested testDeactivateFromActive");
            lifecycle.deactivate();
            assertFalse(lifecycle.isActive());
            assertEquals("INACTIVE", lifecycle.getStatus());
        }

    }

}
