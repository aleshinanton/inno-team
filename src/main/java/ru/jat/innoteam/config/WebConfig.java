package ru.jat.innoteam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class WebConfig {
    @Bean
    @Profile("!compose")
    public RestTemplate defaultRestTemplate() {
        final var restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:1937"));
        return restTemplate;
    }

    @Bean
    @Profile("compose")
    public RestTemplate composeRestTemplate() {
        final var restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://inno_team_python:1937"));
        return restTemplate;
    }
}
