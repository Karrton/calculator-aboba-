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
    private final HistoryService historyService;

    public CalculatorService(HistoryService historyService) {
        this.calculator = new Calculator();
        this.historyService = historyService;
    }

    /**
     * Выполняет математическую операцию на основе запроса.
     *
     * @param request запрос с операцией и операндами
     * @param sessionId идентификатор сессии
     * @return ответ с результатом
     */
    public OperationResponse calculate(OperationRequest request, String sessionId) {
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

        OperationResponse response = new OperationResponse(result, operation, expression);

        // Сохраняем в историю
        if (sessionId != null && !sessionId.isEmpty()) {
            historyService.saveToHistory(request, response, sessionId);
        }

        return response;
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
