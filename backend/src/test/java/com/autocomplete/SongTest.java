package com.autocomplete;

import com.autocomplete.model.Song;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SongTest {

    @Test
    void testGetTitle() {
        Song song = new Song();
        song.setTitle("Test Title");
        assertEquals("Test Title", song.getTitle());
    }

    @Test
    void testSetTitle() {
        Song song = new Song();
        song.setTitle("Test Title");
        assertEquals("Test Title", song.getTitle());
    }

    @Test
    void testGetLength() {
        Song song = new Song();
        song.setLength("3:30");
        assertEquals("3:30", song.getLength());
    }

    @Test
    void testSetLength() {
        Song song = new Song();
        song.setLength("3:30");
        assertEquals("3:30", song.getLength());
    }

    @Test
    void testGetArtist() {
        Song song = new Song();
        song.setArtist("Test Artist");
        assertEquals("Test Artist", song.getArtist());
    }

    @Test
    void testSetArtist() {
        Song song = new Song();
        song.setArtist("Test Artist");
        assertEquals("Test Artist", song.getArtist());
    }

    @Test
    void testGetAlbum() {
        Song song = new Song();
        song.setAlbum("Test Album");
        assertEquals("Test Album", song.getAlbum());
    }

    @Test
    void testSetAlbum() {
        Song song = new Song();
        song.setAlbum("Test Album");
        assertEquals("Test Album", song.getAlbum());
    }
}
