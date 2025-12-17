import React from 'react';
import './App.css';
import './i18n/i18n';
import Calculator from './components/Calculator';
import LanguageSwitcher from './components/LanguageSwitcher';

function App() {
  return (
    <div className="App">
      <LanguageSwitcher />
      <Calculator />
    </div>
  );
}

export default App;
