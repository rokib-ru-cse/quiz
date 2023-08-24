package com.bitspondon.quiz.infrastructure.service.async.sameobject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class ApiService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public ApiService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public CompletableFuture<Comment[]> callApi() {
        return CompletableFuture.supplyAsync(() -> {
            String response = restTemplate.getForObject("https://jsonplaceholder.typicode.com/comments", String.class);
            try {
                return objectMapper.readValue(response, Comment[].class);
            } catch (Exception e) {
                throw new RuntimeException("Error deserializing response", e);
            }
        });
    }
}
