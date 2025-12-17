package com.calculator.controller;

import com.calculator.dto.OperationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тесты для CalculatorController.
 */
@SpringBootTest
@AutoConfigureMockMvc
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddOperation() throws Exception {
        OperationRequest request = new OperationRequest("ADD", 5.0, 3.0);

        mockMvc.perform(post("/api/calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(8.0))
                .andExpect(jsonPath("$.operation").value("ADD"));
    }

    @Test
    void testSqrtOperation() throws Exception {
        OperationRequest request = new OperationRequest("SQRT", 16.0, null);

        mockMvc.perform(post("/api/calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(4.0));
    }

    @Test
    void testGetOperations() throws Exception {
        mockMvc.perform(get("/api/calculator/operations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testHealthCheck() throws Exception {
        mockMvc.perform(get("/api/calculator/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Calculator service is running"));
    }
}
