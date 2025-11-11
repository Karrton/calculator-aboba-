package com.calculator;

/**
 * Основной класс калькулятора.
 */
public class Calculator {

    /**
     * Сложение двух чисел.
     *
     * @param a первое число
     * @param b второе число
     * @return сумма чисел
     */
    public double add(double a, double b) {
        return a + b;
    }

    /**
     * Вычитание двух чисел.
     *
     * @param a первое число
     * @param b второе число
     * @return разность чисел
     */
    public double subtract(double a, double b) {
        return a - b;
    }

    /**
     * Извлечение квадратного корня.
     *
     * @param a число, из которого извлекается корень
     * @return квадратный корень числа
     * @throws IllegalArgumentException если число отрицательное
     */
    public double sqrt(double a) {
        if (a < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of negative number");
        }
        return Math.sqrt(a);
    }
}