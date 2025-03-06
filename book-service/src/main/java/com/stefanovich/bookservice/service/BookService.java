package com.stefanovich.bookservice.service;

import com.stefanovich.bookservice.model.Book;
import com.stefanovich.bookservice.repository.BookRepository;
import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookInsertLockService bookInsertLockService;
    private final TransactionTemplate transactionTemplate;

    @PostConstruct
    public void init() {
        bookInsertLockService.insertBookWithLock();
//        transactionTemplate.
    }

    @WithSpan("BookService getById")
    public Book getById(@SpanAttribute Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("book with id " + id + " not found"));
    }
}
