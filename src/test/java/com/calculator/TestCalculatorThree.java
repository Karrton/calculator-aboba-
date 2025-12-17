package com.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestCalculatorThree {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    /**
     * Интеграционный тест: проверка комбинированных операций add -> sqrt -> subtract.
     * Проверяет корректность работы с дробными числами.
     */
    @Test
    void testIntegrationMixedOperations() {
        // Складываем 20.5 + 29.5 = 50
        double step1 = calculator.add(20.5, 29.5);
        assertEquals(50.0, step1, 0.001);

        // Извлекаем корень из 50 ≈ 7.071
        double step2 = calculator.sqrt(step1);
        assertEquals(7.071, step2, 0.001);

        // Вычитаем 7.071 - 2.071 = 5
        double result = calculator.subtract(step2, 2.071);
        assertEquals(5.0, result, 0.001);
    }

    /**
     * Unit тест: проверка сложения с отрицательными числами.
     */
    @Test
    void testAddWithNegativeNumbers() {
        assertEquals(0.0, calculator.add(5, -5), 0.001);
        assertEquals(-10.0, calculator.add(-5, -5), 0.001);
        assertEquals(5.0, calculator.add(10, -5), 0.001);
    }

    /**
     * Unit тест: проверка сложения с дробными числами высокой точности.
     */
    @Test
    void testAddDecimalPrecision() {
        double result = calculator.add(0.1, 0.2);
        // Проверяем, что результат близок к 0.3 с учетом погрешности вычислений с плавающей точкой
        assertTrue(Math.abs(result - 0.3) < 0.001);
        assertEquals(12.345, calculator.add(10.12, 2.225), 0.001);
    }
}
