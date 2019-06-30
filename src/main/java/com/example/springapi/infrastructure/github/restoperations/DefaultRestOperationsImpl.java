package com.example.springapi.infrastructure.github.restoperations;

import java.net.URI;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestOperations;

@RequiredArgsConstructor
public class DefaultRestOperationsImpl implements DefaultRestOperations {

    private final RestOperations restOperations;

    @Override
    @Retryable(
            include = {HttpServerErrorException.class, ResourceAccessException.class},
            maxAttemptsExpression = "#{@defaultRestOperationsProperties.getMaxAttempts()}"
    )
    public <T> Optional<T> get(URI uri, HttpHeaders httpHeaders, Class<T> responseType) {
        RequestEntity.HeadersBuilder headersBuilder = RequestEntity.get(uri);
        if (httpHeaders != null) {
            httpHeaders.forEach((key, valueList) ->
                    valueList.forEach(value ->
                            headersBuilder.header(key, value)
                    )
            );
        }
        RequestEntity requestEntity = headersBuilder.build();

        T body = this.restOperations.exchange(requestEntity, responseType).getBody();
        return Optional.ofNullable(body);
    }

    @Override
    @Retryable(
            include = {HttpServerErrorException.class, ResourceAccessException.class},
            maxAttemptsExpression = "#{@defaultRestOperationsProperties.getMaxAttempts()}"
    )
    public <T, K> Optional<T> put(URI uri, HttpHeaders httpHeaders, K requestBody, Class<T> responseType) {
        RequestEntity.BodyBuilder bodyBuilder = RequestEntity.put(uri);
        if (httpHeaders != null) {
            httpHeaders.forEach((key, valueList) ->
                    valueList.forEach(value ->
                            bodyBuilder.header(key, value)
                    )
            );
        }
        RequestEntity requestEntity = bodyBuilder.body(requestBody);

        T responseBody = this.restOperations.exchange(requestEntity, responseType).getBody();
        return Optional.ofNullable(responseBody);
    }
}
