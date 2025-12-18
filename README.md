## Навигация по проекту

### Быстрые ссылки:

#### Backend (Spring Boot + Swagger)
- [REST API Documentation (Swagger)](http://localhost:8080/swagger-ui.html) - после запуска
- [Backend Source Code](src/main/java/com/calculator/)
  - [CalculatorController.java](src/main/java/com/calculator/controller/CalculatorController.java) - REST API endpoints
  - [CalculatorService.java](src/main/java/com/calculator/service/CalculatorService.java) - Бизнес-логика
  - [Operation.java](src/main/java/com/calculator/Operation.java) - Поддерживаемые операции
  - [HistoryService.java](src/main/java/com/calculator/service/HistoryService.java) - Сервис истории
  - [SettingsService.java](src/main/java/com/calculator/service/SettingsService.java) - Сервис настроек
- [Database Models](src/main/java/com/calculator/model/)
- [Application Config](src/main/resources/application.properties)

#### Frontend (React + i18n)
- [Frontend Application](http://localhost:3000) - после запуска
- [Frontend Source Code](frontend/src/)
  - [Calculator.js](frontend/src/components/Calculator.js) - **Экран 1**: Калькулятор (реализован)
  - [i18n Configuration](frontend/src/i18n/i18n.js) - Мультиязычность (RU/EN)
  - [API Services](frontend/src/services/) - Интеграция с backend

#### Проектирование экранов
- [History Screen Design](docs/screens/history-screen.md) - **Экран 2**: История вычислений (проект)
- [Settings Screen Design](docs/screens/settings-screen.md) - **Экран 3**: Настройки (проект)
- [Frontend Architecture](docs/architecture/frontend-architecture.md) - Архитектура фронтенда

#### Тесты
- [Backend Tests](src/test/java/com/calculator/)
  - [CalculatorControllerTest.java](src/test/java/com/calculator/controller/CalculatorControllerTest.java) - Интеграционные тесты API
  - [HistoryServiceTest.java](src/test/java/com/calculator/service/HistoryServiceTest.java) - Unit тесты сервисов
  - [TestCalculatorOne.java](src/test/java/com/calculator/TestCalcutatorOne.java) - Unit тесты калькулятора
  - [TestCalculatorThree.java](src/test/java/com/calculator/TestCalculatorThree.java)
  - [test_calculator_two.java](src/test/java/com/calculator/test_calculator_two.java)
  - [TestCalcutatorFour.java](src/test/java/com/calculator/TestCalcutatorFour.java)
- [CI/CD Pipeline](.github/workflows/ci.yml)

#### Запуск проекта
```bash
# Backend (Spring Boot)
./gradlew bootRun
# Доступен на: http://localhost:8080
# Swagger UI: http://localhost:8080/swagger-ui.html

# Frontend (React)
cd frontend
npm install
npm start
# Доступен на: http://localhost:3000
```

---

## ЛР 4 - Fullstack Calculator

### Реализованный функционал:

#### Backend:
- Spring Boot REST API
- Swagger/OpenAPI документация
- H2 Database (in-memory)
- История вычислений (по session_id)
- Настройки пользователя (язык, тема, точность)
- Операции: ADD, SUBTRACT, MULTIPLY, DIVIDE, SQRT

#### Frontend:
- React 18.2 приложение
- Мультиязычность (RU/EN) с i18next
- Калькулятор с операциями
- Session management (UUID)
- Интеграция с backend API

#### Документация:
- Проектирование History экрана (Markdown + диаграммы)
- Проектирование Settings экрана (Markdown + диаграммы)
- Архитектурная документация

### Разделение работы:

| Участник | Задачи |
|----------|--------|
| Бердышев Даниил | REST API, Swagger, Controllers |
| Юхновец Владимир  | Database, History, Settings Services |
| Мерзляков Владимир | React App, Calculator Screen, i18n |
| Деревнин Илья | UI Design, Documentation, Screens |

### Git Workflow:
- Epic ветка: `epic/fullstack-calculator`
- Feature ветки: `feature/backend-api`, `feature/backend-database`, `feature/frontend-calculator`
- Тестирование: `test/two_unit_and_integration`

---

## ЛР 2

### Канбан-доска проекта:
- [Project «feat»](https://github.com/users/Karrton/projects/1)

### Список всех задач:
- [Issues](https://github.com/Karrton/calculator-aboba-/issues)

#### Ссылки на отдельные задачи:
- [Задача на добавление операции "Модуль"](https://github.com/Karrton/calculator-aboba-/issues/29) - [прикрепленная ветка](https://github.com/Karrton/calculator-aboba-/tree/feat%2F29-add-the-moduloremainder-operation-modulo)
- [Задача на добавление операции "Квадратный корень"(выполнена)](https://github.com/Karrton/calculator-aboba-/issues/26) - [прикрепленная ветка](https://github.com/Karrton/calculator-aboba-/tree/feat/26-add-function-sqrt)
- [Задача на добавление операции "Степень"](https://github.com/Karrton/calculator-aboba-/issues/27) - [прикрепленная ветка](https://github.com/Karrton/calculator-aboba-/tree/feat%2F27-add-the-power-operation)
