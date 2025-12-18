# Frontend Architecture

## Общая архитектура

```
┌────────────────────────────────────────────────────────┐
│                      React Application                 │
├────────────────────────────────────────────────────────┤
│                                                        │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐  │
│  │  Calculator  │  │   History    │  │   Settings   │  │
│  │    Screen    │  │    Screen    │  │    Screen    │  │
│  └──────────────┘  └──────────────┘  └──────────────┘  │
│         │                 │                  │         │
│         └─────────────────┴──────────────────┘         │
│                           │                            │
│                  ┌────────▼────────┐                   │
│                  │   App Context   │                   │
│                  │  (State Mgmt)   │                   │
│                  └────────┬────────┘                   │
│                           │                            │
│         ┌─────────────────┼─────────────────┐          │
│         │                 │                 │          │
│  ┌──────▼──────┐  ┌──────▼──────┐  ┌──────▼──────┐     │
│  │  Calculator │  │   History   │  │  Settings   │     │
│  │   Service   │  │   Service   │  │   Service   │     │
│  └─────────────┘  └─────────────┘  └─────────────┘     │
│         │                 │                 │          │
│         └─────────────────┴─────────────────┘          │
│                           │                            │
│                  ┌────────▼────────┐                   │
│                  │   API Client    │                   │
│                  │   (Axios)       │                   │
│                  └────────┬────────┘                   │
│                           │                            │
│  ┌────────────────────────┴─────────────────────────┐  │
│  │               Session Manager                    │  │
│  │         (localStorage + UUID)                    │  │
│  └──────────────────────────────────────────────────┘  │
│                                                        │
└────────────────────────────────────────────────────────┘
                            │
                            │ HTTP/JSON
                            ▼
┌─────────────────────────────────────────────────────────┐
│                    Backend REST API                     │
│                  (Spring Boot)                          │
└─────────────────────────────────────────────────────────┘
```

## Компонентная структура

```
src/
├── components/
│   ├── Calculator/
│   │   ├── Calculator.js         (Главный компонент)
│   │   ├── Display.js            (Дисплей)
│   │   ├── Button.js             (Кнопка)
│   │   └── OperationButtons.js   (Панель операций)
│   │
│   ├── History/
│   │   ├── History.js            (Главный компонент)
│   │   ├── HistoryList.js        (Список записей)
│   │   ├── HistoryItem.js        (Запись)
│   │   ├── SearchBar.js          (Поиск)
│   │   └── ConfirmDialog.js      (Подтверждение)
│   │
│   ├── Settings/
│   │   ├── Settings.js           (Главный компонент)
│   │   ├── LanguageSelector.js   (Выбор языка)
│   │   ├── ThemeSelector.js      (Выбор темы)
│   │   ├── PrecisionSlider.js    (Слайдер точности)
│   │   └── InfoSection.js        (Информация)
│   │
│   └── Common/
│       ├── Header.js             (Шапка)
│       ├── Button.js             (Общая кнопка)
│       ├── Modal.js              (Модальное окно)
│       ├── Toast.js              (Уведомления)
│       └── Loader.js             (Загрузка)
│
├── services/
│   ├── api.js                    (Axios instance)
│   ├── calculatorService.js      (Калькулятор API)
│   ├── historyService.js         (История API)
│   └── settingsService.js        (Настройки API)
│
├── context/
│   ├── AppContext.js             (Глобальное состояние)
│   └── ThemeContext.js           (Тема приложения)
│
├── i18n/
│   ├── i18n.js                   (Конфигурация)
│   ├── locales/
│   │   ├── en.json               (Английский)
│   │   └── ru.json               (Русский)
│
├── utils/
│   ├── sessionManager.js         (Управление сессией)
│   ├── formatters.js             (Форматирование)
│   └── validators.js             (Валидация)
│
└── styles/
    ├── global.css                (Глобальные стили)
    ├── themes.css                (Темы)
    └── components/               (Стили компонентов)
```

## Data Flow

```
┌────────────────────────────────────────────────────────────┐
│                       User Action                          │
└────────────────────┬───────────────────────────────────────┘
                     │
                     ▼
┌────────────────────────────────────────────────────────────┐
│                  React Component                           │
│                  (Button Click)                            │
└────────────────────┬───────────────────────────────────────┘
                     │
                     ▼
┌────────────────────────────────────────────────────────────┐
│                  Event Handler                             │
│              (handleButtonClick)                           │
└────────────────────┬───────────────────────────────────────┘
                     │
                     ▼
┌────────────────────────────────────────────────────────────┐
│                   Service Layer                            │
│              (calculatorService.calculate)                 │
└────────────────────┬───────────────────────────────────────┘
                     │
                     ▼
┌────────────────────────────────────────────────────────────┐
│                   API Client                               │
│           (axios.post with sessionId)                      │
└────────────────────┬───────────────────────────────────────┘
                     │
                     │ HTTP Request
                     ▼
┌────────────────────────────────────────────────────────────┐
│                 Backend API                                │
│         (Spring Boot REST Controller)                      │
└────────────────────┬───────────────────────────────────────┘
                     │
                     │ HTTP Response
                     ▼
┌────────────────────────────────────────────────────────────┐
│                   API Client                               │
│              (Response handling)                           │
└────────────────────┬───────────────────────────────────────┘
                     │
                     ▼
┌────────────────────────────────────────────────────────────┐
│                   Service Layer                            │
│            (Data transformation)                           │
└────────────────────┬───────────────────────────────────────┘
                     │
                     ▼
┌────────────────────────────────────────────────────────────┐
│                  React Component                           │
│                  (setState)                                │
└────────────────────┬───────────────────────────────────────┘
                     │
                     ▼
┌────────────────────────────────────────────────────────────┐
│                   Re-render                                │
│               (UI Update)                                  │
└────────────────────────────────────────────────────────────┘
```

## State Management Strategy

```
Application State
├── Local State (useState)
│   └── Используется для: UI состояния, формы, временные данные
│
├── Context API (useContext)
│   └── Используется для: Настройки, тема, язык
│
└── Server State (API)
    └── Используется для: История, настройки пользователя
```

## Session Management

```
User Opens App
     │
     ▼
Check localStorage
     │
     ├─ Session ID exists?
     │   │
     │   ├─ YES → Use existing
     │   │
     │   └─ NO → Generate new UUID
     │         │
     │         └─ Save to localStorage
     │
     ▼
Add to all API requests
(X-Session-Id header)
```

## Локализация (i18n) Flow

```
User Changes Language
     │
     ▼
i18n.changeLanguage(lng)
     │
     ├─ Update i18n state
     ├─ Save to localStorage
     └─ Update backend settings
     │
     ▼
Re-render all components
with new translations
```

## Тема (Theme) Flow

```
User Changes Theme
     │
     ▼
Toggle theme in Context
     │
     ├─ Update CSS variables
     ├─ Add/remove 'dark-theme' class
     ├─ Save to localStorage
     └─ Update backend settings
     │
     ▼
Re-render with new theme
```
