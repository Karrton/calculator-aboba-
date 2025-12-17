package com.calculator.service;

import com.calculator.dto.HistoryEntryDto;
import com.calculator.dto.OperationRequest;
import com.calculator.dto.OperationResponse;
import com.calculator.model.CalculationHistory;
import com.calculator.repository.CalculationHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Тесты для HistoryService.
 */
class HistoryServiceTest {

    @Mock
    private CalculationHistoryRepository historyRepository;

    @InjectMocks
    private HistoryService historyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveToHistory() {
        OperationRequest request = new OperationRequest("ADD", 5.0, 3.0);
        OperationResponse response = new OperationResponse(8.0, "ADD", "5.0 + 3.0 = 8.0");
        String sessionId = "test-session";

        when(historyRepository.save(any(CalculationHistory.class)))
            .thenReturn(new CalculationHistory());

        historyService.saveToHistory(request, response, sessionId);

        verify(historyRepository, times(1)).save(any(CalculationHistory.class));
    }

    @Test
    void testGetHistory() {
        String sessionId = "test-session";
        CalculationHistory history1 = new CalculationHistory("ADD", 5.0, 3.0, 8.0, "5.0 + 3.0 = 8.0", sessionId);
        CalculationHistory history2 = new CalculationHistory("SQRT", 16.0, null, 4.0, "√16.0 = 4.0", sessionId);

        when(historyRepository.findBySessionIdOrderByTimestampDesc(sessionId))
            .thenReturn(Arrays.asList(history1, history2));

        List<HistoryEntryDto> result = historyService.getHistory(sessionId);

        assertEquals(2, result.size());
        verify(historyRepository, times(1)).findBySessionIdOrderByTimestampDesc(sessionId);
    }

    @Test
    void testClearHistory() {
        String sessionId = "test-session";

        doNothing().when(historyRepository).deleteBySessionId(sessionId);

        historyService.clearHistory(sessionId);

        verify(historyRepository, times(1)).deleteBySessionId(sessionId);
    }
}
