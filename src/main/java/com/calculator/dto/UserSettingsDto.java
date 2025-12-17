package com.calculator.dto;

import com.calculator.model.UserSettings;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * DTO для настроек пользователя.
 */
public class UserSettingsDto {

    @NotNull(message = "Language is required")
    @Pattern(regexp = "en|ru", message = "Language must be 'en' or 'ru'")
    private String language;

    @NotNull(message = "Theme is required")
    @Pattern(regexp = "light|dark", message = "Theme must be 'light' or 'dark'")
    private String theme;

    @NotNull(message = "Precision is required")
    @Min(value = 0, message = "Precision must be at least 0")
    @Max(value = 10, message = "Precision must be at most 10")
    private Integer precision;

    public UserSettingsDto() {
    }

    public UserSettingsDto(UserSettings settings) {
        this.language = settings.getLanguage();
        this.theme = settings.getTheme();
        this.precision = settings.getPrecision();
    }

    public UserSettingsDto(String language, String theme, Integer precision) {
        this.language = language;
        this.theme = theme;
        this.precision = precision;
    }

    // Getters and Setters

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }
}
