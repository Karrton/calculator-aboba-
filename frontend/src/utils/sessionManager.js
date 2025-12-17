/**
 * Менеджер сессий пользователя
 */
const sessionManager = {
  /**
   * Получает или создает Session ID
   * @returns {string} Session ID
   */
  getSessionId: () => {
    let sessionId = localStorage.getItem('sessionId');
    if (!sessionId) {
      // Генерируем простой UUID
      sessionId = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        const r = Math.random() * 16 | 0;
        const v = c === 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
      });
      localStorage.setItem('sessionId', sessionId);
    }
    return sessionId;
  },

  /**
   * Очищает Session ID
   */
  clearSessionId: () => {
    localStorage.removeItem('sessionId');
  }
};

export default sessionManager;
