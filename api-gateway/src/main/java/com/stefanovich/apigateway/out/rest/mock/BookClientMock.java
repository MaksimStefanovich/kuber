package com.stefanovich.apigateway.out.rest.mock;

import com.stefanovich.apigateway.out.rest.BookClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "services.book.active", havingValue = "false")
public class BookClientMock implements BookClient {

    @Override
    public String getBookById(int id) {
        return "book";
    }
}
