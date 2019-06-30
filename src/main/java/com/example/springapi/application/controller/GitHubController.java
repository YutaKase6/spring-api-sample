package com.example.springapi.application.controller;

import com.example.springapi.application.resource.ErrorResponse;
import com.example.springapi.domain.service.GitHubService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * ユーザ操作のコントローラ
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/github")
@Api(tags = "GitHub")
public class GitHubController {

    private final GitHubService gitHubService;

    @ApiResponses({
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class),
    })
    @GetMapping("/readme")
    @ResponseStatus(HttpStatus.OK)
    public String findReadme() {
        return this.gitHubService.findReadme();
    }

}
