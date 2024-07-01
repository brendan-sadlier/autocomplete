import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';

const AlbumDetails = () => {
  const { title } = useParams();
  const [album, setAlbum] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    setLoading(true);
    fetch(`http://localhost:8080/api/album/${title}`)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        setAlbum(data);
        setLoading(false);
      })
      .catch(error => {
        console.error('Error fetching album details:', error);
        setError('Failed to fetch album details');
        setLoading(false);
      });
  }, [title]);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  if (!album) {
    return <div>No album found</div>;
  }

  return (
    <div className="details album-detail">
      <h2 className="details-header">{album.title}</h2>
      <div className="content">
        <div className="info">
          <p>
            <strong>Artist: </strong>
            <Link className='details-link' to={`/artist/${album.artist}`}>
              {album.artist}
            </Link>
          </p>
          <p><strong>Description:</strong> {album.description}</p>
        </div>
        <div className="tracks">
          <h3><strong>Tracks</strong></h3>
          {album.songs.length === 0 ? (
            <p>No tracks available</p>
          ) : (
            <ul>
              {album.songs.map((song, index) => (
                <li key={index}>
                  <Link className="details-link" to={`/song/${song.title}`}>
                    {song.title}
                  </Link>
                </li>
              ))}
            </ul>
          )}
        </div>
      </div>
    </div>
  );
}

export default AlbumDetails;