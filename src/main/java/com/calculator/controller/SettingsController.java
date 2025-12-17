package com.calculator.controller;

import com.calculator.dto.UserSettingsDto;
import com.calculator.service.SettingsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST контроллер для работы с настройками пользователя.
 */
@RestController
@RequestMapping("/api/settings")
@CrossOrigin(origins = "*")
@Tag(name = "Settings", description = "User settings API")
public class SettingsController {

    private final SettingsService settingsService;

    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    /**
     * Получает настройки пользователя.
     *
     * @param sessionId идентификатор сессии
     * @return настройки пользователя
     */
    @GetMapping
    @Operation(summary = "Get user settings",
              description = "Returns user settings for session")
    public ResponseEntity<UserSettingsDto> getSettings(
            @RequestHeader(value = "X-Session-Id", defaultValue = "default") String sessionId) {
        return ResponseEntity.ok(settingsService.getSettings(sessionId));
    }

    /**
     * Обновляет настройки пользователя.
     *
     * @param sessionId идентификатор сессии
     * @param settings новые настройки
     * @return обновленные настройки
     */
    @PutMapping
    @Operation(summary = "Update user settings",
              description = "Updates user settings for session")
    public ResponseEntity<UserSettingsDto> updateSettings(
            @RequestHeader(value = "X-Session-Id", defaultValue = "default") String sessionId,
            @Valid @RequestBody UserSettingsDto settings) {
        return ResponseEntity.ok(settingsService.updateSettings(sessionId, settings));
    }
}
