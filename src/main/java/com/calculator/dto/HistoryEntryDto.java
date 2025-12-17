package com.calculator.dto;

import com.calculator.model.CalculationHistory;
import java.time.LocalDateTime;

/**
 * DTO для записи истории вычислений.
 */
public class HistoryEntryDto {

    private Long id;
    private String operation;
    private Double operandA;
    private Double operandB;
    private Double result;
    private String expression;
    private LocalDateTime timestamp;

    public HistoryEntryDto() {
    }

    public HistoryEntryDto(CalculationHistory history) {
        this.id = history.getId();
        this.operation = history.getOperation();
        this.operandA = history.getOperandA();
        this.operandB = history.getOperandB();
        this.result = history.getResult();
        this.expression = history.getExpression();
        this.timestamp = history.getTimestamp();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Double getOperandA() {
        return operandA;
    }

    public void setOperandA(Double operandA) {
        this.operandA = operandA;
    }

    public Double getOperandB() {
        return operandB;
    }

    public void setOperandB(Double operandB) {
        this.operandB = operandB;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
