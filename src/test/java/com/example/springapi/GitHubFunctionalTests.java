package com.example.springapi;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 8081)
public class GitHubFunctionalTests {

    private static final String BASE_PATH = "/v1/github/readme";

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.port = this.port;
    }

    @Test
    public void findReadme() {
        RestAssured.get(BASE_PATH)
                .then()
                .contentType(equalTo(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"))
                .statusCode(HttpStatus.OK.value())
                .body(equalTo("# spring-api-sample"));

        verify(1, getRequestedFor(urlEqualTo("/YutaKase6/spring-api-sample/master/README.md")));
    }
}

