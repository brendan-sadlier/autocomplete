package com.autocomplete;

import com.autocomplete.config.WebConfig;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class WebConfigTest {

    @Test
    void testCorsConfigurer() {
        WebConfig config = new WebConfig();
        WebMvcConfigurer webMvcConfigurer = config.corsConfigurer();
        CorsRegistry registry = new CorsRegistry();

        assertDoesNotThrow(() -> webMvcConfigurer.addCorsMappings(registry));
    }
}
