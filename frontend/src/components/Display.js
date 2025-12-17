import React from 'react';
import '../styles/Display.css';

/**
 * Компонент дисплея калькулятора
 */
const Display = ({ value, expression }) => {
  return (
    <div className="display-container">
      <div className="display-expression">{expression || '\u00A0'}</div>
      <div className="display-value">{value || '0'}</div>
    </div>
  );
};

export default Display;
