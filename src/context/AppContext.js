import React, { createContext, useState, useContext, useEffect } from 'react';
import settingsService from '../services/settingsService';
import { useTranslation } from 'react-i18next';

const AppContext = createContext();

export const useApp = () => {
  const context = useContext(AppContext);
  if (!context) {
    throw new Error('useApp must be used within AppProvider');
  }
  return context;
};

export const AppProvider = ({ children }) => {
  const { i18n } = useTranslation();
  const [theme, setTheme] = useState('light');
  const [precision, setPrecision] = useState(2);
  const [loading, setLoading] = useState(true);

  // Загрузка настроек при монтировании
  useEffect(() => {
    loadSettings();
  }, []);

  // Применение темы
  useEffect(() => {
    document.body.classList.toggle('dark-theme', theme === 'dark');
  }, [theme]);

  const loadSettings = async () => {
    try {
      // Сначала из localStorage
      const localTheme = localStorage.getItem('theme');
      const localLang = localStorage.getItem('language');
      const localPrecision = localStorage.getItem('precision');

      if (localTheme) setTheme(localTheme);
      if (localLang) i18n.changeLanguage(localLang);
      if (localPrecision) setPrecision(parseInt(localPrecision));

      // Затем загружаем с backend
      const settings = await settingsService.getSettings();
      setTheme(settings.theme);
      setPrecision(settings.precision);
      i18n.changeLanguage(settings.language);

      // Синхронизируем с localStorage
      localStorage.setItem('theme', settings.theme);
      localStorage.setItem('language', settings.language);
      localStorage.setItem('precision', settings.precision);
    } catch (error) {
      console.error('Failed to load settings:', error);
    } finally {
      setLoading(false);
    }
  };

  const updateSettings = async (newSettings) => {
    try {
      const updated = await settingsService.updateSettings(newSettings);

      if (newSettings.theme !== undefined) {
        setTheme(newSettings.theme);
        localStorage.setItem('theme', newSettings.theme);
      }

      if (newSettings.language !== undefined) {
        i18n.changeLanguage(newSettings.language);
        localStorage.setItem('language', newSettings.language);
      }

      if (newSettings.precision !== undefined) {
        setPrecision(newSettings.precision);
        localStorage.setItem('precision', newSettings.precision);
      }

      return updated;
    } catch (error) {
      console.error('Failed to update settings:', error);
      throw error;
    }
  };

  const value = {
    theme,
    precision,
    loading,
    updateSettings
  };

  return <AppContext.Provider value={value}>{children}</AppContext.Provider>;
};
