package com.calculator.controller;

import com.calculator.dto.HistoryEntryDto;
import com.calculator.service.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST контроллер для работы с историей вычислений.
 */
@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = "*")
@Tag(name = "History", description = "Calculation history API")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    /**
     * Получает всю историю вычислений.
     *
     * @param sessionId идентификатор сессии
     * @return список записей истории
     */
    @GetMapping
    @Operation(summary = "Get calculation history",
              description = "Returns all calculation history for session")
    public ResponseEntity<List<HistoryEntryDto>> getHistory(
            @RequestHeader(value = "X-Session-Id", defaultValue = "default") String sessionId) {
        return ResponseEntity.ok(historyService.getHistory(sessionId));
    }

    /**
     * Получает последние 10 записей истории.
     *
     * @param sessionId идентификатор сессии
     * @return список последних записей
     */
    @GetMapping("/recent")
    @Operation(summary = "Get recent history",
              description = "Returns last 10 calculations")
    public ResponseEntity<List<HistoryEntryDto>> getRecentHistory(
            @RequestHeader(value = "X-Session-Id", defaultValue = "default") String sessionId) {
        return ResponseEntity.ok(historyService.getRecentHistory(sessionId));
    }

    /**
     * Очищает всю историю.
     *
     * @param sessionId идентификатор сессии
     */
    @DeleteMapping
    @Operation(summary = "Clear history",
              description = "Deletes all calculation history for session")
    public ResponseEntity<Void> clearHistory(
            @RequestHeader(value = "X-Session-Id", defaultValue = "default") String sessionId) {
        historyService.clearHistory(sessionId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Удаляет конкретную запись из истории.
     *
     * @param id идентификатор записи
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete history entry",
              description = "Deletes specific calculation from history")
    public ResponseEntity<Void> deleteHistoryEntry(@PathVariable Long id) {
        historyService.deleteHistoryEntry(id);
        return ResponseEntity.noContent().build();
    }
}
