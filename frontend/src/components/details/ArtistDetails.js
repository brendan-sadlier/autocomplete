import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

import "./Details.css";

const ArtistDetails = () => {

  const { name } = useParams();
  const [artist, setArtist] = useState(null);
  
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    setLoading(true);
    fetch(`http://localhost:8080/api/artist/${name}`)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        setArtist(data);
        setLoading(false);
      })
      .catch(error => {
        console.error('Error fetching artist details:', error);
        setError('Failed to fetch artist details');
        setLoading(false);
      });
  }, [name])

  if (loading) {
    return <div>Loading...</div>
  }

  if (error) {
    return <div>{error}</div>
  }

  if (!artist) {
    return <div>No Artist Found</div>;
  }

  return (
    <div className="details">
      
      <h2 className="details-header">{artist.name}</h2>

      <div className="details-content">

        <h3><strong>Albums: </strong></h3>

        {artist.albums.length === 0 ? (
          <p>No albums available</p>
        ) : (
          <ul>
            {artist.albums.map((album, index) => (
              <li key={index}>
                {album.title}
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  )
}

export default ArtistDetails;