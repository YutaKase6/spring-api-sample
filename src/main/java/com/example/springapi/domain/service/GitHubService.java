package com.example.springapi.domain.service;

import com.example.springapi.domain.repository.GitHubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * ユーザ操作のロジック
 */
@Service
@RequiredArgsConstructor
public class GitHubService {

    private final GitHubRepository gitHubRepository;

    public String findReadme() {
        return this.gitHubRepository.findReadme();
    }

}
