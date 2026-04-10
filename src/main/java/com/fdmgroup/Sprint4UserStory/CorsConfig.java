package com.fdmgroup.Sprint4UserStory;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Tells Spring Boot this is a configuration class
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // Configure CORS to allow frontend requests
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        // apply to all endpoints
        registry.addMapping("/**")
                // React app URL
                .allowedOrigins("http://localhost:3000")
                // HTTP methods
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH");
    }
}