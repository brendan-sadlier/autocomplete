import { AudioLines, Clock8Icon, DiscAlbum, Music, User, UserIcon } from "lucide-react";
import "./App.css";

function formatSeconds(seconds) {
  let minutes = Math.floor(seconds / 60);
  const remainingSeconds = seconds % 60;
  const formattedSeconds = remainingSeconds < 10 ? `0${remainingSeconds}` : remainingSeconds;
  return `${minutes}:${formattedSeconds}`;
}

const suggestionFormatter = (suggestion) => {

  const getIcon = (type) => {
    switch (type) {
      case 'Song':
        return <Music />;
      case 'Album':
      case 'AlbumDescription':
        return <DiscAlbum />;
      case 'Artist':
        return <User />;
      default:
        return <AudioLines />;
    }
  };

  return (
    <>
    <span className="results-icon">{getIcon(suggestion.type)}</span>
      <span className="results-value">{suggestion.value}</span>
      {suggestion.type === "Song" && (
        <span className="results-details">
          <UserIcon className="artist-icon" />
          {suggestion.artist}
          <Clock8Icon className="song-time-icon" />
          {suggestion.lengthOfSong}
        </span>
      )}

      {suggestion.type === "Album" && (
        <span className="results-details">
          <UserIcon className="artist-icon" />
          {suggestion.artist} - {suggestion.numberOfSongs} songs
        </span>
      )}

      {suggestion.type === "Deezer" && (
        <span className="results-details">
          <UserIcon className="artist-icon" />
          {suggestion.artist}
          <Clock8Icon className="song-time-icon" />
          {formatSeconds(suggestion.lengthOfSong)}
        </span>
      )}
    </>
  )
};

export { suggestionFormatter };