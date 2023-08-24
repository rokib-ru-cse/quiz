package com.bitspondon.quiz.infrastructure.service.async.separateobject.approachone;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

public class Main {


    private final AsyncApiCallService asyncApiCallService;

    public Main(AsyncApiCallService asyncApiCallService) {
        this.asyncApiCallService = asyncApiCallService;
    }

    public Map<String, Object> getResultsFromAllApis() {
        return asyncApiCallService.fetchDataFromAllApis();
    }

    public static void main(String[] args) {
        ApiService apiService = new ApiService(new RestTemplate(), new ObjectMapper());
        Main a = new Main(new AsyncApiCallService(apiService));
        Map<String, Object> resultsFromAllApis = a.getResultsFromAllApis();
        System.out.println(resultsFromAllApis);

        for (Map.Entry<String, Object> entry : resultsFromAllApis.entrySet()) {
            String apiName = entry.getKey();
            Object response = entry.getValue();

            if (response instanceof List) {
                List<?> responseList = (List<?>) response;
                System.out.println("API: " + apiName + ", Response Size: " + responseList.size());
            } else {
                System.out.println("API: " + apiName + ", Response is not a List");
            }
        }
    }
}
