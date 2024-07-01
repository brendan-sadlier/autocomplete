package com.autocomplete.service;

import com.autocomplete.model.Suggestion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeezerSuggestionService {

    private static final Logger logger = LoggerFactory.getLogger(DeezerSuggestionService.class);

    @Value("${deezer.api.url}")
    private String deezerApiUrl;

    public List<Suggestion> searchDeezer(String query) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s/search?q=%s", deezerApiUrl, query);

        try {
            ParameterizedTypeReference<Map<String, Object>> responseType = new ParameterizedTypeReference<Map<String, Object>>() {};
            ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, responseType);

            Map<String, Object> response = Optional.ofNullable(responseEntity.getBody())
                    .orElseThrow(() -> new RuntimeException("Deezer API response body is null"));

            List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");
            if (data == null) {
                throw new RuntimeException("Deezer API response data is null");
            }

            return data.stream()
                    .map(this::mapToSuggestion)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error fetching data from Deezer API: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch data from Deezer API", e);
        }
    }

    private Suggestion mapToSuggestion(Map<String, Object> item) {
        Map<String, Object> artist = (Map<String, Object>) item.get("artist");
        String artistName = artist != null ? (String) artist.get("name") : null;
        String length = item.get("duration") != null ? item.get("duration").toString() : null;
        String link = (String) item.get("link");

        return new Suggestion.Builder((String) item.get("title"), "Deezer", artistName)
                .lengthOfSong(length)
                .description(link)
                .build();
    }
}
