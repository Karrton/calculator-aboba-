import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';

const resources = {
  en: {
    translation: {
      // Calculator screen (будет добавлено Person 3)
      calculator: {
        title: "Calculator"
      },

      // Buttons
      buttons: {
        back: "Back",
        cancel: "Cancel",
        delete: "Delete",
        save: "Save"
      },

      // History screen
      history: {
        title: "History",
        empty: "History is empty",
        emptyDesc: "Perform calculations and they will appear here",
        search: "Search expressions...",
        clearAll: "Clear All",
        confirmClear: "Clear history?",
        confirmDesc: "This action cannot be undone",
        cancel: "Cancel",
        delete: "Delete",
        today: "Today",
        yesterday: "Yesterday",
        loadMore: "Load More"
      },

      // Settings screen
      settings: {
        title: "Settings",
        language: "Language",
        theme: "Theme",
        light: "Light",
        dark: "Dark",
        precision: "Calculation Precision",
        precisionDesc: "Decimal places",
        info: "Information",
        version: "Version",
        api: "API",
        sessionId: "Session ID",
        save: "Save Changes",
        saved: "Settings saved",
        connected: "Connected",
        disconnected: "Disconnected"
      }
    }
  },
  ru: {
    translation: {
      // Calculator screen (будет добавлено Person 3)
      calculator: {
        title: "Калькулятор"
      },

      // Buttons
      buttons: {
        back: "Назад",
        cancel: "Отмена",
        delete: "Удалить",
        save: "Сохранить"
      },

      // History screen
      history: {
        title: "История",
        empty: "История пуста",
        emptyDesc: "Выполните вычисления, и они появятся здесь",
        search: "Поиск по выражениям...",
        clearAll: "Очистить все",
        confirmClear: "Очистить историю?",
        confirmDesc: "Это действие нельзя отменить",
        cancel: "Отмена",
        delete: "Удалить",
        today: "Сегодня",
        yesterday: "Вчера",
        loadMore: "Загрузить еще"
      },

      // Settings screen
      settings: {
        title: "Настройки",
        language: "Язык",
        theme: "Тема",
        light: "Светлая",
        dark: "Темная",
        precision: "Точность вычислений",
        precisionDesc: "Знаков после запятой",
        info: "Информация",
        version: "Версия",
        api: "API",
        sessionId: "ID сессии",
        save: "Сохранить изменения",
        saved: "Настройки сохранены",
        connected: "Подключено",
        disconnected: "Отключено"
      }
    }
  }
};

i18n
  .use(initReactI18next)
  .init({
    resources,
    lng: localStorage.getItem('language') || 'en',
    fallbackLng: 'en',
    interpolation: {
      escapeValue: false
    }
  });

export default i18n;
