package com.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты для класса Calculator.
 * Включает 1 интеграционный тест и 2 unit теста.
 */
class TestCalculatorOne {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    /**
     * Интеграционный тест: проверка цепочки операций add -> add -> sqrt.
     * Тестирует взаимодействие между методами калькулятора.
     */
    @Test
    void testIntegrationChainedOperations() {
        // Сначала складываем 3 + 5 = 8
        double step1 = calculator.add(3, 5);
        assertEquals(8.0, step1, 0.001);

        // Затем добавляем 8 + 8 = 16
        double step2 = calculator.add(step1, 8);
        assertEquals(16.0, step2, 0.001);

        // Извлекаем корень из 16 = 4
        double result = calculator.sqrt(step2);
        assertEquals(4.0, result, 0.001);
    }

    /**
     * Unit тест: проверка сложения положительных чисел.
     */
    @Test
    void testAddPositiveNumbers() {
        assertEquals(10.0, calculator.add(7, 3), 0.001);
        assertEquals(100.5, calculator.add(50.25, 50.25), 0.001);
    }

    /**
     * Unit тест: проверка sqrt с положительным числом.
     */
    @Test
    void testSqrtPositiveNumber() {
        assertEquals(5.0, calculator.sqrt(25), 0.001);
        assertEquals(3.0, calculator.sqrt(9), 0.001);
        assertEquals(0.0, calculator.sqrt(0), 0.001);
    }
}
