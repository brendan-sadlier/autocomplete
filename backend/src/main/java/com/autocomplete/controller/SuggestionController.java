package com.autocomplete.controller;

import com.autocomplete.model.Album;
import com.autocomplete.model.Artist;
import com.autocomplete.model.Song;
import com.autocomplete.model.Suggestion;
import com.autocomplete.service.DeezerSuggestionService;
import com.autocomplete.service.LocalSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SuggestionController {

    private final LocalSuggestionService localSuggestionService;
    private final DeezerSuggestionService deezerSuggestionService;

    @Autowired
    public SuggestionController(LocalSuggestionService localSuggestionService, DeezerSuggestionService deezerSuggestionService) {
        this.localSuggestionService = localSuggestionService;
        this.deezerSuggestionService = deezerSuggestionService;
    }

    @GetMapping("/suggestions")
    public ResponseEntity<List<Suggestion>> getSuggestions(@RequestParam @NotBlank String query) {
        List<Suggestion> suggestions = localSuggestionService.getSuggestions(query);
        if (suggestions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(suggestions);
    }

    @GetMapping("/deezer")
    public ResponseEntity<List<Suggestion>> searchDeezer(@RequestParam @NotBlank String query) {
        List<Suggestion> suggestions = deezerSuggestionService.searchDeezer(query);
        if (suggestions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(suggestions);
    }

    @GetMapping("/artist/{name}")
    public ResponseEntity<Artist> getArtistDetails(@PathVariable @NotBlank String name) {
        Optional<Artist> artist = localSuggestionService.getArtistDetails(name);
        return artist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/album/{name}")
    public ResponseEntity<Album> getAlbumDetails(@PathVariable @NotBlank String name) {
        Optional<Album> album = localSuggestionService.getAlbumDetails(name);
        return album.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/song/{name}")
    public ResponseEntity<Song> getSongDetails(@PathVariable @NotBlank String name) {
        Optional<Song> song = localSuggestionService.getSongDetails(name);
        return song.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
