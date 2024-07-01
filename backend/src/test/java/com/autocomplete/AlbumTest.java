package com.autocomplete;

import com.autocomplete.model.Album;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlbumTest {

    @Test
    void testAlbum() {
        Album album = new Album();
        album.setTitle("Test Album");
        album.setSongs(Collections.singletonList(new Song()));
        album.setDescription("Test Description");
        album.setArtist("Test Artist");

        assertEquals("Test Album", album.getTitle());
        assertEquals(1, album.getSongs().size());
        assertEquals("Test Description", album.getDescription());
        assertEquals("Test Artist", album.getArtist());
    }
}
