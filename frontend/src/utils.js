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

export { suggestionFormatter };