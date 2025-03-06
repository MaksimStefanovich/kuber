package com.stefanovich.bookservice.service;

import com.stefanovich.bookservice.model.Book;
import com.stefanovich.bookservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MigrationService {
    private final BookRepository bookRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertBook() {
        bookRepository.save(Book.builder()
                .name("Test1").build());
        bookRepository.save(Book.builder()
                .name("Test2").build());
    }
}
