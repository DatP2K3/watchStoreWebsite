package com.watch.store.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String s) {
        super(s);
    }
}
