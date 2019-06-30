package com.example.springapi.infrastructure.github.repository;

import com.example.springapi.domain.exception.NotFoundException;
import com.example.springapi.domain.repository.GitHubRepository;
import com.example.springapi.infrastructure.github.restoperations.DefaultRestOperations;
import java.net.URI;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;

/**
 * 永続化の実装クラス
 * ドメインオブジェクトをEntityに変換してJPAをラップする
 */
@Repository
@RequiredArgsConstructor
@ConfigurationProperties("env.github-repository")
public class GitHubRepositoryImpl implements GitHubRepository {
    private static final String BASE_PATH = "/YutaKase6/spring-api-sample/master/README.md";

    private final DefaultRestOperations defaultRestOperations;

    @Getter
    @Setter
    private String entryPoint;

    /**
     * {@inheritDoc}
     */
    @Override
    public String findReadme() {
        URI uri = URI.create(this.entryPoint + BASE_PATH);
        return this.defaultRestOperations.get(uri, new HttpHeaders(), String.class)
                .orElseThrow(() -> new NotFoundException("Response is null."));
    }
}
