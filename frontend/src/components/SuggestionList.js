import React from 'react'
import PropTypes from 'prop-types'

import "./SuggestionList.css"

const SuggestionList = ({ suggestions, onSuggestionClick, suggestionFormatter, searchPerformed }) => {

  if (searchPerformed && suggestions.length === 0) {
    return (
      <div className="suggestion-item">No suggestions found</div>
    )
  }

  return (

    <ul className='suggestion-list'>
      { suggestions.map((suggestions, index) => (
        <li
          key={index}
          onClick={() => onSuggestionClick(suggestions)}
          className="suggestion-item"
        >
          {suggestionFormatter(suggestions)}
        </li>
      ))}
    </ul>
  )
}

SuggestionList.propTypes = {
  suggestions: PropTypes.arrayOf(PropTypes.object).isRequired,
  onSuggestionClick: PropTypes.func.isRequired,
  suggestionFormatter: PropTypes.func.isRequired,
  searchPerformed: PropTypes.bool.isRequired
};

export default SuggestionList