package com.calculator.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 * Entity для хранения истории вычислений.
 */
@Entity
@Table(name = "calculation_history")
public class CalculationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String operation;

    @Column(nullable = false)
    private Double operandA;

    @Column
    private Double operandB;

    @Column(nullable = false)
    private Double result;

    @Column(nullable = false)
    private String expression;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column
    private String sessionId;

    public CalculationHistory() {
        this.timestamp = LocalDateTime.now();
    }

    public CalculationHistory(String operation, Double operandA, Double operandB,
                            Double result, String expression, String sessionId) {
        this.operation = operation;
        this.operandA = operandA;
        this.operandB = operandB;
        this.result = result;
        this.expression = expression;
        this.sessionId = sessionId;
        this.timestamp = LocalDateTime.now();
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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
