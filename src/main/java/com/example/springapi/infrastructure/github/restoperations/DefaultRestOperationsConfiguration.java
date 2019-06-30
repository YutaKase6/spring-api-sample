package com.example.springapi.infrastructure.github.restoperations;

import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DefaultRestOperationsConfiguration {

    @Bean
    @ConfigurationProperties("env.default-rest-operations")
    DefaultRestOperationsProperties defaultRestOperationsProperties() {
        return new DefaultRestOperationsProperties();
    }

    @Bean
    DefaultRestOperations defaultRestOperations(DefaultRestOperationsProperties defaultRestOperationsProperties) {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                .requestFactory(SimpleClientHttpRequestFactory::new);

        RestTemplate restTemplate = restTemplateBuilder
                .setReadTimeout(Duration.ofMillis(defaultRestOperationsProperties.getReadTimeout()))
                .setConnectTimeout(Duration.ofMillis(defaultRestOperationsProperties.getConnectTimeout()))
                .build();

        return new DefaultRestOperationsImpl(restTemplate);
    }

}
