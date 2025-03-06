package com.stefanovich.bookservice.exception;

public class ApplicationStartupException extends RuntimeException{
    public ApplicationStartupException(String message) {
        super(message);
    }
}
