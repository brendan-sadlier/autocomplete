package com.autocomplete;

import com.autocomplete.model.Album;
import com.autocomplete.model.Artist;
import com.autocomplete.model.Song;
import com.autocomplete.model.Suggestion;
import com.autocomplete.service.LocalSuggestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocalSuggestionServiceTest {

    @InjectMocks
    private LocalSuggestionService localSuggestionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        localSuggestionService.init();
    }

    @Test
    void testGetSuggestions() {
        List<Suggestion> suggestions = localSuggestionService.getSuggestions("test");
        assertTrue(suggestions.isEmpty());
    }

    @Test
    void testGetArtistDetails() {
        Optional<Artist> artist = localSuggestionService.getArtistDetails("test artist");
        assertTrue(artist.isEmpty());
    }

    @Test
    void testGetAlbumDetails() {
        Optional<Album> album = localSuggestionService.getAlbumDetails("test album");
        assertTrue(album.isEmpty());
    }

    @Test
    void testGetSongDetails() {
        Optional<Song> song = localSuggestionService.getSongDetails("test song");
        assertTrue(song.isEmpty());
    }
}
