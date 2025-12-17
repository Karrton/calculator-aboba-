package com.calculator.dto;

import jakarta.validation.constraints.NotNull;

/**
 * DTO для запроса математической операции.
 */
public class OperationRequest {

    @NotNull(message = "Operation type is required")
    private String operation;

    @NotNull(message = "First operand is required")
    private Double a;

    private Double b; // Опционально для унарных операций

    public OperationRequest() {
    }

    public OperationRequest(String operation, Double a, Double b) {
        this.operation = operation;
        this.a = a;
        this.b = b;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Double getA() {
        return a;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public Double getB() {
        return b;
    }

    public void setB(Double b) {
        this.b = b;
    }
}
