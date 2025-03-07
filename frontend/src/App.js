import React, { useState, useEffect } from 'react';
import './App.css';
import Autocomplete from './components/Autocomplete';
import { suggestionFormatter } from './utils';
import { Route, Routes, useNavigate } from 'react-router-dom';
import ArtistDetails from './components/details/ArtistDetails';
import AlbumDetails from './components/details/AlbumDetails';
import SongDetails from './components/details/SongDetails';
import { Moon, Sun } from 'lucide-react';

function App() {

  const [theme, setTheme] = useState('light');

  const [searchType, setSearchType] = useState('local');

  const navigate = useNavigate();

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

  const handleSuggestionClick = (suggestion) => {
  
    switch (suggestion.type) {
      case 'Artist':
        console.log('Artist: ' + suggestion.value);
        navigate(`/artist/${suggestion.value}`);
        break;
      case 'Album':
        console.log('Album: ' + suggestion.value);
        navigate(`/album/${suggestion.value}`);
        break;
      case 'Song':
        console.log('Song: ' + suggestion.value);
        navigate(`/song/${suggestion.value}`);
        break;
      case 'Deezer':
        console.log('Deezer: ' + suggestion.value);
        window.open(suggestion.description, '_blank')
        break;
      default:
        break;
    }
  }

  return (
    <div className={`${theme} App search-container`}>
      <div className='header'>
        <h1 className='page-title'>Music <span>Search</span></h1>
        <button className='theme-toggle-button' onClick={toggleTheme}>
          {theme === 'light' ? <Sun/> : <Moon/>}
        </button>
      </div>
      <div className='search-type-container'>

        <label className='radio-label'>
          <input 
            className='search-type-input'
            type='radio'
            value='local'
            checked={searchType === 'local'}
            onChange={handleSearchTypeChange}
          />
          Local File Search
        </label>

        <label className='radio-label'>
          <input 
            className='search-type-input'
            type='radio'
            value='external'
            checked={searchType === 'external'}
            onChange={handleSearchTypeChange}
          />
          Deezer Song Search
        </label>


      </div>
      <Autocomplete 
        localSearchUrl={'http://localhost:8080/api/suggestions'}
        externalSearchUrl={'http://localhost:8080/api/deezer'}
        searchType={searchType}
        placeholder='Search for Music'
        suggestionFormatter={suggestionFormatter}
        onSuggestionClick={handleSuggestionClick}
      />

      <div className='details-page'>
        <Routes>
          <Route path='/' />
          <Route path='/artist/:name' Component={ArtistDetails} />
          <Route path='/album/:title' Component={AlbumDetails} />
          <Route path='/song/:title' Component={SongDetails} />
          <Route path='/*' element={<div>404 Not Found</div>} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
