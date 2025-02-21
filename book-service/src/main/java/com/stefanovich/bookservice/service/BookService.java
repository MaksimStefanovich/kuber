package com.stefanovich.bookservice.service;

import com.stefanovich.bookservice.model.Book;
import com.stefanovich.bookservice.repository.BookRepository;
import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookInsertLockService bookInsertLockService;

    @PostConstruct
    public void init() {
        bookInsertLockService.insertBookWithLock();
    }

    @WithSpan("BookService getById")
    public Book getById(@SpanAttribute Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("book with id " + id + " not found"));
    }
}
