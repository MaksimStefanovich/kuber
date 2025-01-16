package com.stefanovich.apigateway.out.rest.impl;

import com.stefanovich.apigateway.out.rest.BookClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConditionalOnProperty(name = "services.book.active", havingValue = "true", matchIfMissing = true)
public class BookClientImpl implements BookClient {

    private final RestTemplate restTemplate;
    @Value("${services.book.url}")
    private String bookUrl;

    public BookClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getBookById(int id) {
        return restTemplate.getForEntity(bookUrl + "/api/v1/books/" + id, String.class).getBody();

    }
}
