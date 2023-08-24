package com.bitspondon.quiz.infrastructure.service.async.separateobject.approachone;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class AsyncApiCallService {

    private final ApiService apiService;

    public AsyncApiCallService(ApiService apiService) {
        this.apiService = apiService;
    }

    public Map<String, Object> fetchDataFromAllApis() {
        List<CompletableFuture<?>> futures = List.of(
                apiService.callCommentsApi(),
                apiService.callProductsApi()
                // Call other API methods...
        );

        CompletableFuture<Void> allOf = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        );

        AtomicInteger i = new AtomicInteger(1);
        return (Map<String, Object>) allOf.thenApply(
                v -> futures.stream()
                        .collect(Collectors.toMap(
                                future -> getApiNameFromMethod(future) + i.getAndIncrement(),
                                CompletableFuture::join))
        ).join();

    }

    private String getApiNameFromMethod(CompletableFuture<?> future) {
        return future.thenApply(Object::getClass)
                .thenApply(Class::getSimpleName)
                .join();
    }
}

