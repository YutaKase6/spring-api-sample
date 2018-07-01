package com.example.springapi.application.exception;

import lombok.Getter;

/**
 * 操作しようとしたリソースが存在しない場合にthrowされるException
 */
@Getter
public class NotFoundException extends RuntimeException {

    /**
     * 操作しようとしたリソース情報
     */
    private final String resource;

    public NotFoundException(String message, String resource) {
        super(resource + " is Not Found: " + message);
        this.resource = resource;
    }
}
