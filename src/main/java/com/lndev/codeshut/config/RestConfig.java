package com.lndev.codeshut.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Value("${json.placeholder.url}")
    private String BASE_URL;

    @Bean
    @Qualifier("postRestClient")
    public RestClient getRestClient(){
        return RestClient.builder()
        .baseUrl(BASE_URL)
        .defaultHeader("Content-Type", "application/json")
        .build();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    
}
