import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';

// Переводы
const resources = {
  en: {
    translation: {
      title: "Web Calculator",
      display: "Display",
      clear: "Clear",
      delete: "Delete",
      operations: {
        add: "Add",
        subtract: "Subtract",
        multiply: "Multiply",
        divide: "Divide",
        sqrt: "Square Root",
        power: "Power",
        modulo: "Modulo"
      },
      buttons: {
        calculate: "Calculate",
        history: "History",
        settings: "Settings",
        back: "Back"
      },
      errors: {
        network: "Network error. Please check your connection.",
        calculation: "Calculation error",
        invalid: "Invalid operation"
      },
      language: "Language",
      theme: "Theme",
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
      title: "Веб Калькулятор",
      display: "Дисплей",
      clear: "Очистить",
      delete: "Удалить",
      operations: {
        add: "Сложение",
        subtract: "Вычитание",
        multiply: "Умножение",
        divide: "Деление",
        sqrt: "Квадратный корень",
        power: "Степень",
        modulo: "Модуль"
      },
      buttons: {
        calculate: "Вычислить",
        history: "История",
        settings: "Настройки",
        back: "Назад"
      },
      errors: {
        network: "Ошибка сети. Проверьте соединение.",
        calculation: "Ошибка вычисления",
        invalid: "Неверная операция"
      },
      language: "Язык",
      theme: "Тема",
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
