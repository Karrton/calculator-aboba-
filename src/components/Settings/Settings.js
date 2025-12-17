import React from 'react';
import { useTranslation } from 'react-i18next';
import { useApp } from '../../context/AppContext';
import '../../styles/Settings.css';

/**
 * Компонент экрана настроек
 * TODO: Полная реализация согласно docs/screens/settings-screen.md
 */
const Settings = () => {
  const { t, i18n } = useTranslation();
  const { theme, precision, updateSettings } = useApp();

  return (
    <div className="settings-screen">
      <div className="settings-header">
        <button className="back-button">← {t('buttons.back', 'Back')}</button>
        <h1>{t('settings.title')}</h1>
      </div>

      <div className="settings-content">
        {/* Language Selector */}
        <div className="settings-section">
          <h3>🌐 {t('settings.language')}</h3>
          <div className="button-group">
            <button
              className={i18n.language === 'en' ? 'active' : ''}
              onClick={() => updateSettings({ language: 'en' })}
            >
              English
            </button>
            <button
              className={i18n.language === 'ru' ? 'active' : ''}
              onClick={() => updateSettings({ language: 'ru' })}
            >
              Русский
            </button>
          </div>
        </div>

        {/* Theme Selector */}
        <div className="settings-section">
          <h3>🎨 {t('settings.theme')}</h3>
          <div className="button-group">
            <button
              className={theme === 'light' ? 'active' : ''}
              onClick={() => updateSettings({ theme: 'light' })}
            >
              ☀️ {t('settings.light')}
            </button>
            <button
              className={theme === 'dark' ? 'active' : ''}
              onClick={() => updateSettings({ theme: 'dark' })}
            >
              🌙 {t('settings.dark')}
            </button>
          </div>
        </div>

        {/* Precision Slider */}
        <div className="settings-section">
          <h3>🔢 {t('settings.precision')}</h3>
          <p className="section-desc">{t('settings.precisionDesc')}</p>
          <input
            type="range"
            min="0"
            max="10"
            value={precision}
            onChange={(e) => updateSettings({ precision: parseInt(e.target.value) })}
            className="precision-slider"
          />
          <div className="precision-value">{precision} {t('settings.precisionDesc')}</div>
        </div>

        {/* Info Section */}
        <div className="settings-section">
          <h3>📊 {t('settings.info')}</h3>
          <div className="info-list">
            <div className="info-item">
              <span>{t('settings.version')}:</span>
              <span>1.0.0</span>
            </div>
            <div className="info-item">
              <span>{t('settings.api')}:</span>
              <span className="status-connected">{t('settings.connected')} ✓</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Settings;
