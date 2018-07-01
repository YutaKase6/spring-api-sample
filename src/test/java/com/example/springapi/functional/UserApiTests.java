package com.example.springapi.functional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiTests {

    private static final String BASE_PATH = "/v1/users/";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getUserById() throws URISyntaxException {
        String excepted = "{\"id\":\"test_id\",\"value\":\"test_value\"}";

        RequestEntity req = RequestEntity
                .get(new URI(BASE_PATH + "/test_id"))
                .build();
        ResponseEntity actual = this.testRestTemplate.exchange(req, String.class);

        assertThat(actual.getBody().toString()).isEqualTo(excepted);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void getUserById404() throws URISyntaxException {
        String excepted = "{\"Error\":{\"Message\":\"404_id is Not Found.\",\"Detail\":\"\",\"Code\":\"\"}}";

        RequestEntity req = RequestEntity
                .get(new URI(BASE_PATH + "/404_id"))
                .build();
        ResponseEntity actual = this.testRestTemplate.exchange(req, String.class);

        assertThat(actual.getBody().toString()).isEqualTo(excepted);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }


    @Test
    public void postUser() throws URISyntaxException {
        String body = "{\"id\":\"test_post_id\",\"value\":\"test_post_value\"}";
        String excepted = body;

        RequestEntity req = RequestEntity
                .post(new URI(BASE_PATH))
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .body(body);
        ResponseEntity actual = this.testRestTemplate.exchange(req, String.class);

        assertThat(actual.getBody().toString()).isEqualTo(excepted);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void postUserIdSizeOver() throws URISyntaxException {
        String body = "{\"id\":\"aaaaaaaaaaaaaaaaaaa\",\"value\":\"test_post_value\"}";
        String excepted = "{\"Error\":{\"Message\":\"Bad Request.\",\"Detail\":\"id: size must be between 0 and 18; \",\"Code\":\"\"}}";

        RequestEntity req = RequestEntity
                .post(new URI(BASE_PATH))
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .body(body);
        ResponseEntity actual = this.testRestTemplate.exchange(req, String.class);

        assertThat(actual.getBody().toString()).isEqualTo(excepted);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postUserIdBlank() throws URISyntaxException {
        String body = "{\"id\":\"\",\"value\":\"test_post_value\"}";
        String excepted = "{\"Error\":{\"Message\":\"Bad Request.\",\"Detail\":\"id: must not be blank; \",\"Code\":\"\"}}";

        RequestEntity req = RequestEntity
                .post(new URI(BASE_PATH))
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .body(body);
        ResponseEntity actual = this.testRestTemplate.exchange(req, String.class);

        assertThat(actual.getBody().toString()).isEqualTo(excepted);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void deleteUserById() throws URISyntaxException {
        RequestEntity req = RequestEntity
                .delete(new URI(BASE_PATH + "/test_id"))
                .build();
        ResponseEntity actual = this.testRestTemplate.exchange(req, String.class);

        assertThat(actual.getBody()).isNull();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void deleteUserByIdNotFound() throws URISyntaxException {
        String excepted = "{\"Error\":{\"Message\":\"404_id is Not Found.\",\"Detail\":\"\",\"Code\":\"\"}}";
        RequestEntity req = RequestEntity
                .delete(new URI(BASE_PATH + "/404_id"))
                .build();
        ResponseEntity actual = this.testRestTemplate.exchange(req, String.class);

        assertThat(actual.getBody().toString()).isEqualTo(excepted);
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
