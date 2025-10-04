package com.calculator;

/**
 * Перечисление математических операций.
 */
public enum Operation {
    /**
     * Операция сложения.
     */
    ADD("+"),
    
    /**
     * Операция вычитания.
     */
    SUBTRACT("-");

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Получить символ операции.
     *
     * @return символ операции
     */
    public String getSymbol() {
        return symbol;
    }
}