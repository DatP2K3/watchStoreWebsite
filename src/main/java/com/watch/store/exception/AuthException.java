package com.watch.store.exception;

public class AuthException extends RuntimeException {
    private ErrorCode errorCode;
    public AuthException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
