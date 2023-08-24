package com.bitspondon.quiz.infrastructure.service.async.separateobject.approachtwo;

import com.bitspondon.quiz.infrastructure.service.async.sameobject.Comment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

//@Service
public class ApiService {

    //    private final AllBCRepository allBCRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;


    public ApiService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public void fetchAllData() throws ExecutionException, InterruptedException, JsonProcessingException {
        List<CompletableFuture<String>> futures = List.of(
                CompletableFuture.supplyAsync(() -> restTemplate.getForObject("https://jsonplaceholder.typicode.com/comments", String.class)),
                CompletableFuture.supplyAsync(() -> restTemplate.getForObject("https://jsonplaceholder.typicode.com/comments", String.class)),
                CompletableFuture.supplyAsync(() -> restTemplate.getForObject("https://jsonplaceholder.typicode.com/comments", String.class)),
                CompletableFuture.supplyAsync(() -> restTemplate.getForObject("https://jsonplaceholder.typicode.com/comments", String.class)),
                CompletableFuture.supplyAsync(() -> restTemplate.getForObject("https://jsonplaceholder.typicode.com/comments", String.class)),
                CompletableFuture.supplyAsync(() -> restTemplate.getForObject("https://jsonplaceholder.typicode.com/comments", String.class)),
                CompletableFuture.supplyAsync(() -> restTemplate.getForObject("https://jsonplaceholder.typicode.com/comments", String.class)),
                CompletableFuture.supplyAsync(() -> restTemplate.getForObject("https://jsonplaceholder.typicode.com/comments", String.class)),
                CompletableFuture.supplyAsync(() -> restTemplate.getForObject("https://jsonplaceholder.typicode.com/comments", String.class)),
                CompletableFuture.supplyAsync(() -> restTemplate.getForObject("https://jsonplaceholder.typicode.com/comments", String.class))
        );
        CompletableFuture<Void> allOf = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        );

        List<String> results = futures.stream()
                .map(CompletableFuture::join)  // Retrieve the results after they are completed
                .collect(Collectors.toList());  // Collect the results into a list

        List<Comment> commentList = Arrays.asList(objectMapper.readValue(results.get(0), Comment[].class));

//        Product[] secondProductArray = deserializeResponse(secondResult, Product[].class);


//        allOf.get();  // This will block until all futures are completed
//        List<Comment> commentList = new ArrayList<>();
//        List<Product> productList = new ArrayList<>();
//        for (CompletableFuture<String> future : futures) {
//            try {
//                String result = future.get(); // This will block until the individual CompletableFuture is completed
//                if (result != null) {
//                    if (result.contains("postId")) {
//                        Comment[] comments = objectMapper.readValue(result, Comment[].class);
//                        commentList.addAll(Arrays.asList(comments));
//                    }
////                    else if (result.contains("productId")) {
////                        Product[] products = deserializeResponse(result, Product[].class);
////                        productList.addAll(Arrays.asList(products));
////                    }
//                    // Add similar blocks for other models
//                }
//            } catch (InterruptedException | ExecutionException e) {
//                // Handle exceptions if needed
//            }
//        }
        System.out.println("commentList " + commentList.size());

//        return allOf.thenApply(
//                v -> futures.stream()
//                        .collect(Collectors.toMap(
//                                this::getApiNameFromIndex,
//                                future -> {
//                                    String result = future.join();
//                                    return deserializeApiResult(result);
//                                }))
//        ).join();
    }

//    private String getApiNameFromIndex(CompletableFuture<?> future) {
//        int index = futures.indexOf(future);
//        return getApiName(index); // Define your method to get API names based on the index
//    }
//
//    private Object deserializeApiResult(String result) {
//        try {
//            return objectMapper.readValue(result, Comment.class);
//        } catch (IOException e) {
//            throw new RuntimeException("Error deserializing response", e);
//        }
//    }


    public static void main(String[] args) throws ExecutionException, InterruptedException, JsonProcessingException {
        ApiService apiService = new ApiService(new RestTemplate(), new ObjectMapper());
        apiService.fetchAllData();
    }
}

