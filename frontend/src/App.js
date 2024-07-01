import React, { useState, useEffect } from 'react';
import './App.css';
import Autocomplete from './components/Autocomplete';
import { suggestionFormatter, handleSuggestionClick } from './utils';

function App() {

  const [theme, setTheme] = useState('light');

  const [searchType, setSearchType] = useState('local');

  const handleSearchTypeChange = (e) => {
    setSearchType(e.target.value);
  }

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
      <div>

      </div>
      <Autocomplete 
        localSearchUrl={'http://localhost:8080/api/suggestions'}
        externalSearchUrl={'http://localhost:8080/api/deezer'}
        searchType={searchType}
        placeholder='Search for Music'
        suggestionFormatter={suggestionFormatter}
        onSuggestionClick={handleSuggestionClick}
      />
    </div>
  );
}

export default App;
