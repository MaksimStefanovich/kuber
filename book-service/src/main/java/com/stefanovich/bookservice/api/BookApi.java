package com.stefanovich.bookservice.api;

import com.stefanovich.bookservice.model.Book;
import com.stefanovich.bookservice.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
@Slf4j
public class BookApi {
    private final BookService bookService;

    @GetMapping("/{id}")
    public Book getById(@PathVariable Integer id, @RequestHeader Map<String, String> headers) {
        headers.forEach((name, value) -> log.debug("{} : {}", name, value));
        return bookService.getById(id);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEntityNotFoundException(EntityNotFoundException exception) {
        log.warn(exception.getMessage());
        return exception.getMessage();
    }
}
