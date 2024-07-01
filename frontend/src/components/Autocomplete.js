import React, { useState, useEffect } from 'react'
import SuggestionList from './SuggestionList'

import "./Autocomplete.css"

const Autocomplete = ({ localSearchUrl, externalSearchUrl, onSuggestionClick, suggestionFormatter, searchType, placeholder }) => {

  const [query, setQuery] = useState('')
  const [localSuggestions, setLocalSuggestions] = useState([])
  const [externalSuggestions, setExternalSuggestions] = useState([])
  const [searchPerformed, setSearchPerformed] = useState(false)

  const [loading, setLoading] = useState(false)

  useEffect(() => {

    if (query.length === 0) {
      setSearchPerformed(false);
    }

    if (query.length > 1) {
      setLoading(true);
      setSearchPerformed(true);

      if (searchType === "local") {
        fetch(`${localSearchUrl}?query=${query}`)
          .then(res => {
            if (!res.ok) {
              throw new Error('Network response was not ok');
            }
            return res.json();
          })
          .then(data => {
            setLocalSuggestions(data);
            setExternalSuggestions([]);
            setLoading(false);
          })
          .catch(err => {
            console.error("Error fetching suggestions:", err);
            setLoading(false);
          });
      } 
      
      else if (searchType === "external") {
        fetch(`${externalSearchUrl}?query=${query}`)
          .then(res => {
            if (!res.ok) {
              throw new Error('Network response was not ok');
            }
            return res.json();
          })
          .then(data => {
            setExternalSuggestions(data);
            setLocalSuggestions([]);
            setLoading(false);
          })
          .catch(err => {
            console.error("Error fetching external suggestions:", err);
            setLoading(false);
          });
      }
    }

    else {
      setLocalSuggestions([]);
      setExternalSuggestions([]);
      setLoading(false);
    }
  }, [query, localSearchUrl, externalSearchUrl, searchType]);

  const handleSuggestionClick = (suggestion) => {
    setQuery("");
    onSuggestionClick(suggestion);
    setSearchPerformed(false);
    setLocalSuggestions([]);
    setExternalSuggestions([]);
  };

  return (
    <div className='autocomplete'>

      <input 
        className='autocomplete-input'
        type='text'
        value={query}
        onChange={e => setQuery(e.target.value)}
        placeholder={placeholder}
      />

      {loading && <div className='loading'>Loading...</div>}

      <SuggestionList 
        suggestions={searchType === "local" ? localSuggestions : externalSuggestions}
        onSuggestionClick={handleSuggestionClick}
        suggestionFormatter={suggestionFormatter}
        searchPerformed={searchPerformed}
      />

    </div>
  )
}

export default Autocomplete
