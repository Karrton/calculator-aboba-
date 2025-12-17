import React from 'react';
import { useTranslation } from 'react-i18next';
import '../styles/LanguageSwitcher.css';

/**
 * Компонент переключателя языка
 */
const LanguageSwitcher = () => {
  const { i18n } = useTranslation();

  const changeLanguage = (lng) => {
    i18n.changeLanguage(lng);
    localStorage.setItem('language', lng);
  };

  return (
    <div className="language-switcher">
      <button
        className={i18n.language === 'en' ? 'active' : ''}
        onClick={() => changeLanguage('en')}
      >
        EN
      </button>
      <button
        className={i18n.language === 'ru' ? 'active' : ''}
        onClick={() => changeLanguage('ru')}
      >
        RU
      </button>
    </div>
  );
};

export default LanguageSwitcher;
