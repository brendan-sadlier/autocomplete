package com.autocomplete;

import com.autocomplete.model.Album;
import com.autocomplete.model.Artist;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ArtistTest {

    @Test
    void testGetName() {
        Artist artist = new Artist();
        artist.setName("Test Artist");
        assertEquals("Test Artist", artist.getName());
    }

    @Test
    void testSetName() {
        Artist artist = new Artist();
        artist.setName("Test Artist");
        assertEquals("Test Artist", artist.getName());
    }

    @Test
    void testGetAlbums() {
        Artist artist = new Artist();
        Album album = new Album();
        album.setTitle("Test Album");
        artist.setAlbums(Collections.singletonList(album));
        assertNotNull(artist.getAlbums());
        assertEquals(1, artist.getAlbums().size());
        assertEquals("Test Album", artist.getAlbums().get(0).getTitle());
    }

    @Test
    void testSetAlbums() {
        Artist artist = new Artist();
        Album album = new Album();
        album.setTitle("Test Album");
        artist.setAlbums(Collections.singletonList(album));
        assertNotNull(artist.getAlbums());
        assertEquals(1, artist.getAlbums().size());
        assertEquals("Test Album", artist.getAlbums().get(0).getTitle());
    }
}
