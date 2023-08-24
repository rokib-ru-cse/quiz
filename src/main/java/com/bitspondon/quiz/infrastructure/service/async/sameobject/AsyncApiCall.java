package com.bitspondon.quiz.infrastructure.service.async.sameobject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AsyncApiCall {
    private final ApiService apiService;

    public AsyncApiCall(ApiService apiService) {
        this.apiService = apiService;
    }

    public List<Comment> fetchDataAsynchronously() {
        List<CompletableFuture<Comment[]>> futures =
                IntStream.range(0, 40)
                        .mapToObj(i -> apiService.callApi())
                        .collect(Collectors.toList());

        return futures.stream()
                .map(CompletableFuture::join)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }




    public static void main(String[] args) {
        Instant startInstant = Instant.now();
        // Create an instance of the ApiService (assuming ApiService is available)
        ApiService apiService = new ApiService(new RestTemplate(),new ObjectMapper());

        // Create an instance of AsyncApiCall using the ApiService
        AsyncApiCall asyncApiCall = new AsyncApiCall(apiService);

        // Fetch data asynchronously and print the result
        List<Comment> results = asyncApiCall.fetchDataAsynchronously();
        //results.forEach(System.out::println);
        System.out.println("total data "+results.size());






        Instant endInstant = Instant.now();
        Duration duration = Duration.between(startInstant, endInstant);
        long seconds = duration.getSeconds();
        long milliseconds = duration.toMillis();
        System.out.println("Elapsed time in seconds: " + seconds);
        System.out.println("Elapsed time in milliseconds: " + milliseconds);
    }
}
