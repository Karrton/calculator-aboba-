package com.calculator.service;

import com.calculator.Calculator;
import com.calculator.Operation;
import com.calculator.dto.OperationRequest;
import com.calculator.dto.OperationResponse;
import org.springframework.stereotype.Service;

/**
 * Сервис для выполнения математических операций.
 */
@Service
public class CalculatorService {

    private final Calculator calculator;

    public CalculatorService() {
        this.calculator = new Calculator();
    }

    /**
     * Выполняет математическую операцию на основе запроса.
     *
     * @param request запрос с операцией и операндами
     * @return ответ с результатом
     */
    public OperationResponse calculate(OperationRequest request) {
        String operation = request.getOperation().toUpperCase();
        Double a = request.getA();
        Double b = request.getB();

        double result;
        String expression;

        switch (operation) {
            case "ADD":
            case "+":
                if (b == null) {
                    throw new IllegalArgumentException("Second operand is required for ADD operation");
                }
                result = calculator.add(a, b);
                expression = a + " + " + b + " = " + result;
                break;

            case "SUBTRACT":
            case "-":
                if (b == null) {
                    throw new IllegalArgumentException("Second operand is required for SUBTRACT operation");
                }
                result = calculator.subtract(a, b);
                expression = a + " - " + b + " = " + result;
                break;

            case "SQRT":
            case "√":
                result = calculator.sqrt(a);
                expression = "√" + a + " = " + result;
                break;

            default:
                throw new IllegalArgumentException("Unsupported operation: " + operation);
        }

        return new OperationResponse(result, operation, expression);
    }

    /**
     * Возвращает список поддерживаемых операций.
     *
     * @return массив операций
     */
    public Operation[] getSupportedOperations() {
        return Operation.values();
    }
}
