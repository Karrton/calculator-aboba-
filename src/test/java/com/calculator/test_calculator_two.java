package com.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestCalculatorTwo {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    /**
     * Интеграционный тест: проверка последовательных операций subtract -> add -> subtract.
     * Тестирует корректность работы калькулятора при различных операциях.
     */
    @Test
    void testIntegrationSequentialOperations() {
        // Вычитаем 100 - 25 = 75
        double step1 = calculator.subtract(100, 25);
        assertEquals(75.0, step1, 0.001);

        // Добавляем 75 + 25 = 100
        double step2 = calculator.add(step1, 25);
        assertEquals(100.0, step2, 0.001);

        // Вычитаем 100 - 64 = 36
        double result = calculator.subtract(step2, 64);
        assertEquals(36.0, result, 0.001);
    }

    /**
     * Unit тест: проверка вычитания с отрицательными числами.
     */
    @Test
    void testSubtractWithNegativeNumbers() {
        assertEquals(-5.0, calculator.subtract(5, 10), 0.001);
        assertEquals(15.0, calculator.subtract(10, -5), 0.001);
        assertEquals(-15.0, calculator.subtract(-10, 5), 0.001);
    }

    /**
     * Unit тест: проверка обработки исключения для sqrt с отрицательным числом.
     */
    @Test
    void testSqrtNegativeNumberThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.sqrt(-25);
        });
        assertEquals("Cannot calculate square root of negative number", exception.getMessage());
    }
}
