package com.stefanovich.bookservice.service;

import com.stefanovich.bookservice.model.Book;
import com.stefanovich.bookservice.repository.BookRepository;
import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    @PostConstruct
    public void init() {
        //TODO optimistic and pessimistic lock
        bookRepository.save(Book.builder()
                .name("Test1").build());
        bookRepository.save(Book.builder()
                .name("Test2").build());
    }

    @WithSpan("BookService getById")
    public Book getById(@SpanAttribute Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("book with id " + id + " not found"));
    }
}
