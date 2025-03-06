package com.stefanovich.bookservice.service;

import com.stefanovich.bookservice.model.BookInsertPessimisticLock;

public interface BookInsertLockService {
    void  insertBookWithLock();
}
