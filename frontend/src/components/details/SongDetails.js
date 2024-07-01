import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';

import './Details.css';

const SongDetails = () => {
  const { title } = useParams();
  const [song, setSong] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    setLoading(true);
    setError(null);

    fetch(`http://localhost:8080/api/song/${title}`)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        setSong(data);
        setLoading(false);
      })
      .catch(error => {
        console.error('Error fetching song details:', error);
        setError('Error fetching song details');
        setLoading(false);
      });
  }, [title]);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  if (!song) {
    return <div>No song found</div>;
  }

  return (
    <div className="details">
      <h2 className='details-header'>{song.title}</h2>
      <div className='details-content'>
        <p><strong>Length: </strong> {song.length}</p>
        <p>
          <strong>Artist: </strong> 
          <Link className="details-link" to={`/artist/${song.artist}`}>{song.artist}</Link>
        </p>
        <p>
          <strong>Album: </strong>
          <Link className="details-link" to={`/album/${song.album}`}>{song.album}</Link>
        </p>
      </div>
    </div>
  );
}

export default SongDetails;