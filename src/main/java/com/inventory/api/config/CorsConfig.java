package com.inventory.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsFilterConfig() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(final CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins(String.valueOf(Collections.singletonList("*"))).allowCredentials(true).allowedMethods("*").allowedHeaders("*").maxAge(3600);
            }
        };
    }
}
