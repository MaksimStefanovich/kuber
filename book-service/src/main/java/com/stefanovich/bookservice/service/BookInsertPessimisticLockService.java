package com.stefanovich.bookservice.service;

import com.stefanovich.bookservice.exception.ApplicationStartupException;
import com.stefanovich.bookservice.repository.BookInsertPessimisticLockRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@ConditionalOnProperty(value = "migration.lock.mode", havingValue = "pessimistic")
@RequiredArgsConstructor
@Slf4j
public class BookInsertPessimisticLockService implements BookInsertLockService {
    private final BookInsertPessimisticLockRepo bookInsertPessimisticLockRepo;
    private final MigrationService migrationService;

    @Transactional(noRollbackFor = Exception.class)
    @Override
    public void insertBookWithLock() {
        log.debug("Try to lock");
        bookInsertPessimisticLockRepo.findForUpdate();
        if (bookInsertPessimisticLockRepo.checkLock()) {
            log.debug("Already locked");
            throw new ApplicationStartupException("Already locked");
        }
        if (bookInsertPessimisticLockRepo.checkSuccess()) {
            log.debug("Already successed");
            return;
        }
        log.debug("Locking");
        bookInsertPessimisticLockRepo.lock();
        migrationService.insertBook();
        bookInsertPessimisticLockRepo.success();
        bookInsertPessimisticLockRepo.unlock();
        log.debug("Unlocking");
    }

}
