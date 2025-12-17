import apiClient from './api';

/**
 * Сервис для работы с настройками
 */
const settingsService = {
  /**
   * Получает настройки пользователя
   * @returns {Promise} объект настроек
   */
  getSettings: async () => {
    try {
      const response = await apiClient.get('/settings');
      return response.data;
    } catch (error) {
      throw error.response?.data || error.message;
    }
  },

  /**
   * Обновляет настройки пользователя
   * @param {Object} settings - новые настройки
   * @returns {Promise} обновленные настройки
   */
  updateSettings: async (settings) => {
    try {
      const response = await apiClient.put('/settings', settings);
      return response.data;
    } catch (error) {
      throw error.response?.data || error.message;
    }
  }
};

export default settingsService;
