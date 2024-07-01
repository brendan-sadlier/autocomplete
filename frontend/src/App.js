import React, { useState, useEffect } from 'react';
import './App.css';

function App() {

  const [theme, setTheme] = useState('light');

  const toggleTheme = () => {
    setTheme(theme => theme === 'light' ? 'dark' : 'light'); 
  }

  useEffect(() => {
    localStorage.setItem('theme', theme);
    document.body.className = theme;
  }, [theme]);

  return (
    <div className="App search-container">
      <div className='header'>
        <h1 className='page-title'>Music <span>Search</span></h1>
        <button className='theme-toggle-button' onClick={toggleTheme}>
          {theme === 'light' ? '🌙' : '☀️'}
        </button>
      </div>
    </div>
  );
}

export default App;
