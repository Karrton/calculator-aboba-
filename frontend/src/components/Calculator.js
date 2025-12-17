import React, { useState, useEffect } from 'react';
import { useTranslation } from 'react-i18next';
import Display from './Display';
import Button from './Button';
import calculatorService from '../services/calculatorService';
import sessionManager from '../utils/sessionManager';
import '../styles/Calculator.css';

/**
 * Главный компонент калькулятора
 */
const Calculator = () => {
  const { t } = useTranslation();
  const [display, setDisplay] = useState('0');
  const [expression, setExpression] = useState('');
  const [firstOperand, setFirstOperand] = useState(null);
  const [operation, setOperation] = useState(null);
  const [waitingForSecondOperand, setWaitingForSecondOperand] = useState(false);
  const [error, setError] = useState(null);

  // Инициализация session ID при загрузке
  useEffect(() => {
    sessionManager.getSessionId();
  }, []);

  // Обработка ввода цифры
  const handleNumberClick = (number) => {
    setError(null);

    if (waitingForSecondOperand) {
      setDisplay(String(number));
      setWaitingForSecondOperand(false);
    } else {
      setDisplay(display === '0' ? String(number) : display + number);
    }
  };

  // Обработка десятичной точки
  const handleDecimalClick = () => {
    if (waitingForSecondOperand) {
      setDisplay('0.');
      setWaitingForSecondOperand(false);
    } else if (display.indexOf('.') === -1) {
      setDisplay(display + '.');
    }
  };

  // Обработка операции
  const handleOperationClick = (op) => {
    const inputValue = parseFloat(display);

    if (firstOperand === null) {
      setFirstOperand(inputValue);
    } else if (operation) {
      // Если уже есть операция, выполняем её
      performCalculation();
    }

    setOperation(op);
    setWaitingForSecondOperand(true);

    // Определяем символ операции для отображения
    let opSymbol = op;
    if (op === 'ADD') opSymbol = '+';
    else if (op === 'SUBTRACT') opSymbol = '-';
    else if (op === 'MULTIPLY') opSymbol = '×';
    else if (op === 'DIVIDE') opSymbol = '÷';

    setExpression(`${inputValue} ${opSymbol}`);
  };

  // Выполнение вычисления
  const performCalculation = async () => {
    const a = firstOperand;
    const b = parseFloat(display);

    if (operation === null) return;

    try {
      const response = await calculatorService.calculate(operation, a, b);
      setDisplay(String(response.result));
      setExpression(response.expression);
      setFirstOperand(response.result);
      setOperation(null);
      setWaitingForSecondOperand(true);
      setError(null);
    } catch (err) {
      setError(err.message || t('errors.calculation'));
      setDisplay('Error');
      console.error('Calculation error:', err);
    }
  };

  // Обработка унарных операций (например, квадратный корень)
  const handleUnaryOperation = async (op) => {
    const a = parseFloat(display);

    try {
      const response = await calculatorService.calculate(op, a);
      setDisplay(String(response.result));
      setExpression(response.expression);
      setFirstOperand(null);
      setOperation(null);
      setWaitingForSecondOperand(true);
      setError(null);
    } catch (err) {
      setError(err.message || t('errors.calculation'));
      setDisplay('Error');
      console.error('Unary operation error:', err);
    }
  };

  // Очистка
  const handleClear = () => {
    setDisplay('0');
    setExpression('');
    setFirstOperand(null);
    setOperation(null);
    setWaitingForSecondOperand(false);
    setError(null);
  };

  // Удаление последнего символа
  const handleBackspace = () => {
    if (display.length > 1 && display !== 'Error') {
      setDisplay(display.slice(0, -1));
    } else {
      setDisplay('0');
    }
  };

  // Обработка знака равно
  const handleEquals = () => {
    if (operation && firstOperand !== null) {
      performCalculation();
    }
  };

  return (
    <div className="calculator">
      <div className="calculator-header">
        <h1>{t('title')}</h1>
      </div>

      <Display value={display} expression={expression} />

      {error && <div className="error-message">{error}</div>}

      <div className="calculator-buttons">
        {/* Первая строка */}
        <Button label="C" onClick={handleClear} variant="function" />
        <Button label="⌫" onClick={handleBackspace} variant="function" />
        <Button label="√" onClick={() => handleUnaryOperation('SQRT')} variant="operator" />
        <Button label="÷" onClick={() => handleOperationClick('DIVIDE')} variant="operator" />

        {/* Вторая строка */}
        <Button label="7" onClick={() => handleNumberClick(7)} />
        <Button label="8" onClick={() => handleNumberClick(8)} />
        <Button label="9" onClick={() => handleNumberClick(9)} />
        <Button label="×" onClick={() => handleOperationClick('MULTIPLY')} variant="operator" />

        {/* Третья строка */}
        <Button label="4" onClick={() => handleNumberClick(4)} />
        <Button label="5" onClick={() => handleNumberClick(5)} />
        <Button label="6" onClick={() => handleNumberClick(6)} />
        <Button label="−" onClick={() => handleOperationClick('SUBTRACT')} variant="operator" />

        {/* Четвертая строка */}
        <Button label="1" onClick={() => handleNumberClick(1)} />
        <Button label="2" onClick={() => handleNumberClick(2)} />
        <Button label="3" onClick={() => handleNumberClick(3)} />
        <Button label="+" onClick={() => handleOperationClick('ADD')} variant="operator" />

        {/* Пятая строка */}
        <Button label="0" onClick={() => handleNumberClick(0)} span={2} />
        <Button label="." onClick={handleDecimalClick} />
        <Button label="=" onClick={handleEquals} variant="equals" />
      </div>
    </div>
  );
};

export default Calculator;
