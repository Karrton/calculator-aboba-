package com.calculator.repository;

import com.calculator.model.CalculationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository для работы с историей вычислений.
 */
@Repository
public interface CalculationHistoryRepository extends JpaRepository<CalculationHistory, Long> {

    /**
     * Находит всю историю для конкретной сессии.
     *
     * @param sessionId идентификатор сессии
     * @return список записей истории
     */
    List<CalculationHistory> findBySessionIdOrderByTimestampDesc(String sessionId);

    /**
     * Находит последние N записей для сессии.
     *
     * @param sessionId идентификатор сессии
     * @return список записей истории
     */
    List<CalculationHistory> findTop10BySessionIdOrderByTimestampDesc(String sessionId);

    /**
     * Удаляет всю историю для сессии.
     *
     * @param sessionId идентификатор сессии
     */
    void deleteBySessionId(String sessionId);
}
