package com.calculator.controller;

import com.calculator.Operation;
import com.calculator.dto.OperationRequest;
import com.calculator.dto.OperationResponse;
import com.calculator.service.CalculatorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST контроллер для операций калькулятора.
 */
@RestController
@RequestMapping("/api/calculator")
@CrossOrigin(origins = "*")
@Tag(name = "Calculator", description = "Calculator operations API")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    /**
     * Выполняет математическую операцию.
     *
     * @param request запрос с операцией и операндами
     * @param sessionId идентификатор сессии
     * @return результат операции
     */
    @PostMapping("/calculate")
    @io.swagger.v3.oas.annotations.Operation(summary = "Calculate operation",
                     description = "Performs a mathematical operation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = OperationResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<OperationResponse> calculate(
            @Valid @RequestBody OperationRequest request,
            @org.springframework.web.bind.annotation.RequestHeader(value = "X-Session-Id", defaultValue = "default") String sessionId) {
        OperationResponse response = calculatorService.calculate(request, sessionId);
        return ResponseEntity.ok(response);
    }

    /**
     * Возвращает список поддерживаемых операций.
     *
     * @return массив операций
     */
    @GetMapping("/operations")
    @io.swagger.v3.oas.annotations.Operation(summary = "Get supported operations",
                     description = "Returns list of all supported operations")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    public ResponseEntity<Operation[]> getOperations() {
        return ResponseEntity.ok(calculatorService.getSupportedOperations());
    }

    /**
     * Health check endpoint.
     *
     * @return статус сервиса
     */
    @GetMapping("/health")
    @io.swagger.v3.oas.annotations.Operation(summary = "Health check",
                     description = "Check if service is running")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Calculator service is running");
    }
}
