package com.example.springapi.infrastructure.github.restoperations;

import lombok.Data;

@Data
public class DefaultRestOperationsProperties {
    private int readTimeout = 1000;
    private int connectTimeout = 2000;
    private int maxAttempts = 3;
}
