import React from 'react';
import { useTranslation } from 'react-i18next';
import '../../styles/History.css';

/**
 * Компонент экрана истории вычислений
 * TODO: Полная реализация согласно docs/screens/history-screen.md
 */
const History = () => {
  const { t } = useTranslation();

  return (
    <div className="history-screen">
      <div className="history-header">
        <button className="back-button">← {t('buttons.back', 'Back')}</button>
        <h1>{t('history.title')}</h1>
        <button className="clear-button">{t('history.clearAll')}</button>
      </div>

      <div className="history-content">
        <div className="search-bar">
          <input
            type="text"
            placeholder={t('history.search')}
            className="search-input"
          />
        </div>

        <div className="history-list">
          {/* TODO: Implement history list */}
          <div className="empty-state">
            <div className="empty-icon">📋</div>
            <h3>{t('history.empty')}</h3>
            <p>{t('history.emptyDesc')}</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default History;
