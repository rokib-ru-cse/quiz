package com.bitspondon.quiz.presentation.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Quiz API")
                        .description("Quiz Rest API")
                        .version("1.0"));
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
