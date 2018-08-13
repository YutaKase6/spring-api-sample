package com.example.springapi.domain.exception;

/**
 * 操作しようとしたリソースが存在しない場合にthrowされるException
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
