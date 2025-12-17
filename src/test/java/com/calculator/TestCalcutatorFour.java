package com.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCalculatorFour {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    /**
     * Интеграционный тест: проверка сложного сценария sqrt -> add -> subtract -> add.
     * Тестирует последовательное выполнение различных операций.
     */
    @Test
    void testIntegrationComplexScenario() {
        // Извлекаем корень из 81 = 9
        double step1 = calculator.sqrt(81);
        assertEquals(9.0, step1, 0.001);

        // Добавляем 9 + 16 = 25
        double step2 = calculator.add(step1, 16);
        assertEquals(25.0, step2, 0.001);

        // Вычитаем 25 - 9 = 16
        double step3 = calculator.subtract(step2, 9);
        assertEquals(16.0, step3, 0.001);

        // Добавляем 16 + 20 = 36
        double result = calculator.add(step3, 20);
        assertEquals(36.0, result, 0.001);
    }

    /**
     * Unit тест: проверка вычитания с нулем.
     */
    @Test
    void testSubtractWithZero() {
        assertEquals(10.0, calculator.subtract(10, 0), 0.001);
        assertEquals(-10.0, calculator.subtract(0, 10), 0.001);
        assertEquals(0.0, calculator.subtract(0, 0), 0.001);
    }

    /**
     * Unit тест: проверка sqrt с граничными значениями.
     */
    @Test
    void testSqrtBoundaryValues() {
        // Корень из 1
        assertEquals(1.0, calculator.sqrt(1), 0.001);

        // Корень из большого числа
        assertEquals(100.0, calculator.sqrt(10000), 0.001);

        // Корень из маленького дробного числа
        assertEquals(0.1, calculator.sqrt(0.01), 0.001);
    }
}
