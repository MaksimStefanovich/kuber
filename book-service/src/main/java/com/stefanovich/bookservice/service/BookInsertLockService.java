package com.stefanovich.bookservice.service;

import com.stefanovich.bookservice.model.Book;
import com.stefanovich.bookservice.repository.BookInsertLockRepo;
import com.stefanovich.bookservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookInsertLockService {
    private final BookInsertLockRepo bookInsertLockRepo;
    private final BookRepository bookRepository;

    public void lock() {
        bookInsertLockRepo.lock();
    }

    public void unlock() {
        bookInsertLockRepo.unlock();
    }

    @SneakyThrows
    @Transactional
    public void insertBookWithLock() {
        bookInsertLockRepo.findForUpdate();
        log.info("Locking");
        bookInsertLockRepo.lock();
        Thread.sleep(30 * 1000);
        extracted();
        bookInsertLockRepo.unlock();
        log.info("Unlocking");
    }

    //TODO:
    private void extracted() {
        bookRepository.save(Book.builder()
                .name("Test1").build());
        bookRepository.save(Book.builder()
                .name("Test2").build());
    }
}
