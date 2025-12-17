import apiClient from './api';

/**
 * Сервис для работы с калькулятором
 */
const calculatorService = {
  /**
   * Выполняет математическую операцию
   * @param {string} operation - тип операции (ADD, SUBTRACT, etc.)
   * @param {number} a - первый операнд
   * @param {number} b - второй операнд (опционально)
   * @returns {Promise} результат операции
   */
  calculate: async (operation, a, b = null) => {
    try {
      const response = await apiClient.post('/calculator/calculate', {
        operation,
        a,
        b
      });
      return response.data;
    } catch (error) {
      throw error.response?.data || error.message;
    }
  },

  /**
   * Получает список поддерживаемых операций
   * @returns {Promise} массив операций
   */
  getOperations: async () => {
    try {
      const response = await apiClient.get('/calculator/operations');
      return response.data;
    } catch (error) {
      throw error.response?.data || error.message;
    }
  },

  /**
   * Проверка работоспособности API
   * @returns {Promise} статус
   */
  healthCheck: async () => {
    try {
      const response = await apiClient.get('/calculator/health');
      return response.data;
    } catch (error) {
      throw error.response?.data || error.message;
    }
  }
};

export default calculatorService;
