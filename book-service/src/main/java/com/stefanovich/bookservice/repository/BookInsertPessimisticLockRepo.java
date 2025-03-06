package com.stefanovich.bookservice.repository;

import com.stefanovich.bookservice.model.BookInsertPessimisticLock;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookInsertPessimisticLockRepo extends JpaRepository<BookInsertPessimisticLock, Long> {
    @Modifying
    @Query("update BookInsertPessimisticLock b set b.locked = true where b.id = 1")
    void lock();

    @Modifying
    @Query("update BookInsertPessimisticLock b set b.locked = false where b.id = 1")
    void unlock();

    @Query("select b.locked from BookInsertPessimisticLock b where b.id = 1")
    boolean checkLock();

    @Query("select b.successed from BookInsertPessimisticLock b where b.id = 1")
    boolean checkSuccess();

    @Modifying
    @Query("update BookInsertPessimisticLock b set b.successed = true where b.id = 1")
    void success();

    @Query("select b from BookInsertPessimisticLock b where b.id = 1")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    void findForUpdate();
}
