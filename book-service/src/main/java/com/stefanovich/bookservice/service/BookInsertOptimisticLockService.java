package com.stefanovich.bookservice.service;

import com.stefanovich.bookservice.exception.ApplicationStartupException;
import com.stefanovich.bookservice.model.BookInsertOptimisticLock;
import com.stefanovich.bookservice.repository.BookInsertOptimisticLockRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@ConditionalOnProperty(value = "migration.lock.mode", havingValue = "optimistic")
@RequiredArgsConstructor
@Slf4j
public class BookInsertOptimisticLockService implements BookInsertLockService {
    private final BookInsertOptimisticLockRepo bookInsertOptimisticLockRepo;
    private final MigrationService migrationService;

    @Transactional(noRollbackFor = Exception.class)
    @Override
    @SneakyThrows
    public void insertBookWithLock() {
        BookInsertOptimisticLock bookInsertOptimisticLock = bookInsertOptimisticLockRepo.findById(1L).orElseThrow();
        bookInsertOptimisticLock.setLocked(true);
        Thread.sleep(10 * 1000);
        bookInsertOptimisticLockRepo.save(bookInsertOptimisticLock);
    }

}
