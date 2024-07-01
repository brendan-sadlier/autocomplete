package com.autocomplete;

import com.autocomplete.controller.SuggestionController;
import com.autocomplete.model.Album;
import com.autocomplete.model.Artist;
import com.autocomplete.model.Song;
import com.autocomplete.model.Suggestion;
import com.autocomplete.service.DeezerSuggestionService;
import com.autocomplete.service.LocalSuggestionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SuggestionController.class)
public class SuggestionControllerTest {

    @Autowired private MockMvc mockMvc;

    @MockBean private LocalSuggestionService localSuggestionService;
    @MockBean private DeezerSuggestionService deezerSuggestionService;

    @Test
    void testGetLocalSuggestions() throws Exception {
        Mockito.when(localSuggestionService.getSuggestions(anyString())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/suggestions").param("query", "test"))
                .andExpect(status().isNoContent());

        Mockito.when(localSuggestionService.getSuggestions(anyString())).thenReturn(Collections.singletonList(new Suggestion.Builder("test", "type", "artist").build()));
        mockMvc.perform(get("/api/suggestions").param("query", "test"))
                .andExpect(status().isOk());
    }

    @Test
    void testSearchDeezer() throws Exception {
        Mockito.when(deezerSuggestionService.searchDeezer(anyString())).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/deezer").param("query", "test"))
                .andExpect(status().isNoContent());

        Mockito.when(deezerSuggestionService.searchDeezer(anyString())).thenReturn(Collections.singletonList(new Suggestion.Builder("test", "type", "artist").build()));
        mockMvc.perform(get("/api/deezer").param("query", "test"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetArtistDetails() throws Exception {
        Mockito.when(localSuggestionService.getArtistDetails(anyString())).thenReturn(Optional.of(new Artist()));
        mockMvc.perform(get("/api/artist/test"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAlbumDetails() throws Exception {
        Mockito.when(localSuggestionService.getAlbumDetails(anyString())).thenReturn(Optional.of(new Album()));
        mockMvc.perform(get("/api/album/test"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetSongDetails() throws Exception {
        Mockito.when(localSuggestionService.getSongDetails(anyString())).thenReturn(Optional.of(new Song()));
        mockMvc.perform(get("/api/song/test"))
                .andExpect(status().isOk());
    }


}
