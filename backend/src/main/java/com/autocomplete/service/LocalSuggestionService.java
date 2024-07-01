package com.autocomplete.service;

import com.autocomplete.model.Album;
import com.autocomplete.model.Artist;
import com.autocomplete.model.Song;
import com.autocomplete.model.Suggestion;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LocalSuggestionService {

    private static final Logger logger = LoggerFactory.getLogger(LocalSuggestionService.class);

    private List<Artist> artists = new ArrayList<>();

    @PostConstruct
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Artist>> typeReference = new TypeReference<List<Artist>>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/data.json");

        try {
            artists = mapper.readValue(inputStream, typeReference);
        } catch (Exception e) {
            logger.error("Unable to load artists: {} " + e.getMessage(), e);
        }
    }

    public List<Suggestion> getSuggestions(String query) {
        String lowerCaseQuery = query.toLowerCase();
        return artists.stream()
                .flatMap(artist -> Stream.concat(
                        Stream.of(new Suggestion.Builder(artist.getName(), "Artist", artist.getName()).build()),
                        Stream.concat(
                                artist.getAlbums().stream().map(album ->
                                        new Suggestion.Builder(album.getTitle(), "Album", artist.getName())
                                                .numberOfSongs(album.getSongs().size())
                                                .description(album.getDescription())
                                                .build()),
                                artist.getAlbums().stream().flatMap(album ->
                                        album.getSongs().stream().map(song ->
                                                new Suggestion.Builder(song.getTitle(), "Song", artist.getName())
                                                        .lengthOfSong(song.getLength())
                                                        .build()))
                        )
                ))
                .filter(suggestion -> isMeaningfulMatch(suggestion.getValue(), lowerCaseQuery))
                .distinct()
                .collect(Collectors.toList());
    }

    private boolean isMeaningfulMatch(String value, String query) {
        value = value.toLowerCase();
        if (value.contains(query)) {
            return true;
        }
        return false;
    }

    public Optional<Artist> getArtistDetails(String name) {
        return artists.stream()
                .filter(artist -> artist.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public Optional<Album> getAlbumDetails(String title) {
        return artists.stream()
                .flatMap(artist -> artist.getAlbums().stream())
                .filter(album -> album.getTitle().equalsIgnoreCase(title))
                .map(album -> {
                    album.setArtist(getArtistByAlbumTitle(album.getTitle()));
                    return album;
                })
                .findFirst();
    }

    public Optional<Song> getSongDetails(String title) {
        return artists.stream()
                .flatMap(artist -> artist.getAlbums().stream())
                .flatMap(album -> album.getSongs().stream())
                .filter(song -> song.getTitle().equalsIgnoreCase(title))
                .map(song -> {
                    song.setArtist(getArtistBySongTitle(song.getTitle()));
                    song.setAlbum(getAlbumBySongTitle(song.getTitle()));
                    return song;
                })
                .findFirst();
    }

    private String getArtistByAlbumTitle(String albumTitle) {
        return artists.stream()
                .filter(artist -> artist.getAlbums().stream()
                        .anyMatch(album -> album.getTitle().equalsIgnoreCase(albumTitle)))
                .map(Artist::getName)
                .findFirst()
                .orElse(null);
    }

    private String getArtistBySongTitle(String songTitle) {
        return artists.stream()
                .filter(artist -> artist.getAlbums().stream()
                        .anyMatch(album -> album.getSongs().stream()
                                .anyMatch(song -> song.getTitle().equalsIgnoreCase(songTitle))))
                .map(Artist::getName)
                .findFirst()
                .orElse(null);
    }

    private String getAlbumBySongTitle(String songTitle) {
        return artists.stream()
                .flatMap(artist -> artist.getAlbums().stream())
                .filter(album -> album.getSongs().stream()
                        .anyMatch(song -> song.getTitle().equalsIgnoreCase(songTitle)))
                .map(Album::getTitle)
                .findFirst()
                .orElse(null);
    }
}
