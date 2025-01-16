package com.stefanovich.apigateway.in.rest;

import com.stefanovich.apigateway.out.rest.BookClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CommonApi {
    private final BookClient bookClient;

    public CommonApi(BookClient bookClient) {
        this.bookClient = bookClient;
    }

    @GetMapping("/hello")
    public String getHello() {
        log.info("Hello");
        return "Hello world";
    }

    @GetMapping("/error")
    public String error() {
        log.info("ERROR");
        throw new RuntimeException();
    }

    @GetMapping("/book/{id}")
    public String getBook(@PathVariable Integer id) {
        log.info("Get book with id {}", id);
        return bookClient.getBookById(id);
    }
}
