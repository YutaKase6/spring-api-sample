package com.example.springapi.infrastructure.github.restoperations;

import java.net.URI;
import java.util.Optional;
import org.springframework.http.HttpHeaders;

public interface DefaultRestOperations {

    <T> Optional<T> get(URI uri, HttpHeaders httpHeaders, Class<T> responseType);

    <T, K> Optional<T> put(URI uri, HttpHeaders httpHeaders, K requestBody, Class<T> responseType);
}
