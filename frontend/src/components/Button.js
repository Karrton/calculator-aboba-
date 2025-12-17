import React from 'react';
import '../styles/Button.css';

/**
 * Компонент кнопки калькулятора
 */
const Button = ({ label, onClick, variant = 'default', disabled = false, span = 1 }) => {
  return (
    <button
      className={`calculator-button ${variant} span-${span}`}
      onClick={onClick}
      disabled={disabled}
    >
      {label}
    </button>
  );
};

export default Button;
