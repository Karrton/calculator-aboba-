package com.calculator.service;

import com.calculator.dto.UserSettingsDto;
import com.calculator.model.UserSettings;
import com.calculator.repository.UserSettingsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Сервис для работы с настройками пользователя.
 */
@Service
public class SettingsService {

    private final UserSettingsRepository settingsRepository;

    public SettingsService(UserSettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    /**
     * Получает настройки пользователя.
     *
     * @param sessionId идентификатор сессии
     * @return настройки пользователя
     */
    public UserSettingsDto getSettings(String sessionId) {
        UserSettings settings = settingsRepository.findBySessionId(sessionId)
            .orElse(createDefaultSettings(sessionId));
        return new UserSettingsDto(settings);
    }

    /**
     * Обновляет настройки пользователя.
     *
     * @param sessionId идентификатор сессии
     * @param settingsDto новые настройки
     * @return обновленные настройки
     */
    @Transactional
    public UserSettingsDto updateSettings(String sessionId, UserSettingsDto settingsDto) {
        UserSettings settings = settingsRepository.findBySessionId(sessionId)
            .orElse(new UserSettings(sessionId, "en", "light", 2));

        settings.setLanguage(settingsDto.getLanguage());
        settings.setTheme(settingsDto.getTheme());
        settings.setPrecision(settingsDto.getPrecision());

        UserSettings saved = settingsRepository.save(settings);
        return new UserSettingsDto(saved);
    }

    /**
     * Создает настройки по умолчанию.
     *
     * @param sessionId идентификатор сессии
     * @return настройки по умолчанию
     */
    private UserSettings createDefaultSettings(String sessionId) {
        UserSettings settings = new UserSettings(sessionId, "en", "light", 2);
        return settingsRepository.save(settings);
    }
}
