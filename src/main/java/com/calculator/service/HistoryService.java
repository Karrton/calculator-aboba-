package com.calculator.service;

import com.calculator.dto.HistoryEntryDto;
import com.calculator.dto.OperationRequest;
import com.calculator.dto.OperationResponse;
import com.calculator.model.CalculationHistory;
import com.calculator.repository.CalculationHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для работы с историей вычислений.
 */
@Service
public class HistoryService {

    private final CalculationHistoryRepository historyRepository;

    public HistoryService(CalculationHistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    /**
     * Сохраняет запись в историю.
     *
     * @param request  запрос операции
     * @param response результат операции
     * @param sessionId идентификатор сессии
     */
    @Transactional
    public void saveToHistory(OperationRequest request, OperationResponse response, String sessionId) {
        CalculationHistory history = new CalculationHistory(
            response.getOperation(),
            request.getA(),
            request.getB(),
            response.getResult(),
            response.getExpression(),
            sessionId
        );
        historyRepository.save(history);
    }

    /**
     * Получает всю историю для сессии.
     *
     * @param sessionId идентификатор сессии
     * @return список записей истории
     */
    public List<HistoryEntryDto> getHistory(String sessionId) {
        return historyRepository.findBySessionIdOrderByTimestampDesc(sessionId)
            .stream()
            .map(HistoryEntryDto::new)
            .collect(Collectors.toList());
    }

    /**
     * Получает последние 10 записей истории.
     *
     * @param sessionId идентификатор сессии
     * @return список последних записей
     */
    public List<HistoryEntryDto> getRecentHistory(String sessionId) {
        return historyRepository.findTop10BySessionIdOrderByTimestampDesc(sessionId)
            .stream()
            .map(HistoryEntryDto::new)
            .collect(Collectors.toList());
    }

    /**
     * Очищает историю для сессии.
     *
     * @param sessionId идентификатор сессии
     */
    @Transactional
    public void clearHistory(String sessionId) {
        historyRepository.deleteBySessionId(sessionId);
    }

    /**
     * Удаляет конкретную запись из истории.
     *
     * @param id идентификатор записи
     */
    @Transactional
    public void deleteHistoryEntry(Long id) {
        historyRepository.deleteById(id);
    }
}
