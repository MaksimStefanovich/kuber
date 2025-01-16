package com.stefanovich.bookservice.repository;

import com.stefanovich.bookservice.model.BookInsertLock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookInsertLockRepo extends JpaRepository<BookInsertLock, Long> {
    @Modifying
    @Query("update BookInsertLock b set b.locked = true where b.id = 1")
    void lock();

    @Modifying
    @Query("update BookInsertLock b set b.locked = false where b.id = 1")
    void unlock();

    @Query("select b.locked from BookInsertLock b where b.id = 1")
    boolean checkLock();
}
