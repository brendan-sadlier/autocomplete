package com.autocomplete;

import com.autocomplete.model.Suggestion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuggestionTest {

    @Test
    void testSuggestionBuilder() {
        Suggestion suggestion = new Suggestion.Builder("Test Title", "Test Type", "Test Artist")
                .numberOfSongs(10)
                .lengthOfSong("3:30")
                .description("Test Description")
                .build();

        assertEquals("Test Title", suggestion.getValue());
        assertEquals("Test Type", suggestion.getType());
        assertEquals("Test Artist", suggestion.getArtist());
        assertEquals(10, suggestion.getNumberOfSongs());
        assertEquals("3:30", suggestion.getLengthOfSong());
        assertEquals("Test Description", suggestion.getDescription());
    }
}
