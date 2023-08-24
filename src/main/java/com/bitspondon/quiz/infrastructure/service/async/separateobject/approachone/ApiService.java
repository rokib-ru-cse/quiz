package com.bitspondon.quiz.infrastructure.service.async.separateobject.approachone;

import com.bitspondon.quiz.infrastructure.service.async.sameobject.Comment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

//@Service
public class ApiService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public ApiService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public CompletableFuture<Comment[]> callCommentsApi() {
        return callApi("https://jsonplaceholder.typicode.com/comments", Comment[].class);
    }

    public CompletableFuture<Comment[]> callProductsApi() {
        return callApi("https://jsonplaceholder.typicode.com/comments", Comment[].class);
    }

    // Similarly, add methods for other APIs...

    private <T> CompletableFuture<T> callApi(String apiUrl, Class<T> responseType) {
        return CompletableFuture.supplyAsync(() -> {
            String response = restTemplate.getForObject(apiUrl, String.class);
            try {
                return objectMapper.readValue(response, responseType);
            } catch (Exception e) {
                throw new RuntimeException("Error deserializing response", e);
            }
        });
    }
}

