import apiClient from './api';

/**
 * Сервис для работы с историей вычислений
 */
const historyService = {
  /**
   * Получает всю историю вычислений
   * @returns {Promise} массив записей истории
   */
  getHistory: async () => {
    try {
      const response = await apiClient.get('/history');
      return response.data;
    } catch (error) {
      throw error.response?.data || error.message;
    }
  },

  /**
   * Получает последние 10 записей истории
   * @returns {Promise} массив последних записей
   */
  getRecentHistory: async () => {
    try {
      const response = await apiClient.get('/history/recent');
      return response.data;
    } catch (error) {
      throw error.response?.data || error.message;
    }
  },

  /**
   * Очищает всю историю
   * @returns {Promise}
   */
  clearHistory: async () => {
    try {
      await apiClient.delete('/history');
    } catch (error) {
      throw error.response?.data || error.message;
    }
  },

  /**
   * Удаляет конкретную запись из истории
   * @param {number} id - ID записи
   * @returns {Promise}
   */
  deleteHistoryEntry: async (id) => {
    try {
      await apiClient.delete(`/history/${id}`);
    } catch (error) {
      throw error.response?.data || error.message;
    }
  }
};

export default historyService;
