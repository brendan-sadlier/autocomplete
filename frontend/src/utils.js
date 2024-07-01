const suggestionFormatter = (suggestion) => {
  return (
    <>
      <span className="results-value">{suggestion.value}</span>
      {suggestion.type === "Song" && (
        <span className="results-details">
          {suggestion.artist}
          {suggestion.lengthOfSong}
        </span>
      )}

      {suggestion.type === "Album" && (
        <span className="results-details">
          {suggestion.artist} - {suggestion.numberOfSongs} songs
        </span>
      )}

      {suggestion.type === "Deezer" && (
        <span className="results-details">
          {suggestion.artist}
          {suggestion.lengthOfSong}
        </span>
      )}
    </>
  )
};

const handleSuggestionClick = (suggestion) => {
  switch (suggestion.type) {
    case 'Artist':
      console.log('Artist: ' + suggestion.value);
      break;
    case 'Album':
      console.log('Album: ' + suggestion.value);
      break;
    case 'Song':
      console.log('Song: ' + suggestion.value);
      break;
    case 'Deezer':
      console.log('Deezer: ' + suggestion.value);
      break;
    default:
      break;
  }
}

export { suggestionFormatter, handleSuggestionClick };