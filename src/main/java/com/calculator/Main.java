package com.calculator;

/**
 * Главный класс приложения калькулятора.
 */
public final class Main {
    
    private Main() {
    }

    /**
     * Точка входа в приложение.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        
        System.out.println("=== Calculator Application ===");
        System.out.println("Demo mode");
        
        double result1 = calculator.add(5, 3);
        System.out.println("5 + 3 = " + result1);
        
        double result2 = calculator.subtract(10, 4);
        System.out.println("10 - 4 = " + result2);
        
        System.out.println("Calculator works!");
    }
}