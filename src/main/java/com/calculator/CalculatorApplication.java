package com.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс Spring Boot приложения калькулятора.
 */
@SpringBootApplication
public class CalculatorApplication {

    /**
     * Точка входа в приложение.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        SpringApplication.run(CalculatorApplication.class, args);
    }
}
