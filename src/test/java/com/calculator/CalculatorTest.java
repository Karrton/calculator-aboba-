package com.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты для класса Calculator.
 */
class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void testAdd() {
        assertEquals(8.0, calculator.add(5, 3), 0.001);
    }

    @Test
    void testSubtract() {
        assertEquals(6.0, calculator.subtract(10, 4), 0.001);
    }
}