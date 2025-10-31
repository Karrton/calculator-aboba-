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
     * Деление двух чисел.
     *
     * @param a делимое
     * @param b делитель
     * @return частное чисел
     * @throws IllegalArgumentException если делитель равен нулю
     */
    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero");
        }
        return a / b;
    }
}