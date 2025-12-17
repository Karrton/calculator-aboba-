package com.calculator.repository;

import com.calculator.model.UserSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository для работы с настройками пользователя.
 */
@Repository
public interface UserSettingsRepository extends JpaRepository<UserSettings, Long> {

    /**
     * Находит настройки по идентификатору сессии.
     *
     * @param sessionId идентификатор сессии
     * @return настройки пользователя
     */
    Optional<UserSettings> findBySessionId(String sessionId);
}
