package com.calculator.dto;

/**
 * DTO для ответа с результатом операции.
 */
public class OperationResponse {

    private Double result;
    private String operation;
    private String expression;

    public OperationResponse() {
    }

    public OperationResponse(Double result, String operation, String expression) {
        this.result = result;
        this.operation = operation;
        this.expression = expression;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
