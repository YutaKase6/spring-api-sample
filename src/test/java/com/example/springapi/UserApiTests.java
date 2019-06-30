package com.example.springapi;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiTests {
    private final static String TABLE = "test_users";
    private static final String SELECT_SQL = String.format("SELECT * FROM %s WHERE ID = ?", TABLE);

    private static final String BASE_PATH = "/v1/users";

    @LocalServerPort
    private int port;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        RestAssured.port = this.port;
    }

    @Test
    public void getUserById() {
        RestAssured.given()
                .basePath(BASE_PATH)
                .get("/{user_id}", "test_id")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo("test_id"))
                .body("value", equalTo("test_value"));
    }

    @Test
    public void getUserById404() {
        RestAssured.given()
                .basePath(BASE_PATH)
                .get("/{user_id}", "404_id")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("Error.Message", equalTo("Not Found"))
                .body("Error.Detail", equalTo(""))
                .body("Error.Code", equalTo(""));
    }

    @Test
    public void postUser() {
        String body = "{" +
                "\"id\": \"test_post_id\"," +
                "\"value\": \"test_post_value\"" +
                "}";

        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body(body)
                .post(BASE_PATH)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", equalTo("test_post_id"))
                .body("value", equalTo("test_post_value"));

        Map<String, Object> actualData = this.jdbcTemplate.queryForMap(SELECT_SQL, "test_post_id");
        assertThat(actualData.get("id")).isEqualTo("test_post_id");
        assertThat(actualData.get("value")).isEqualTo("test_post_value");
    }

    @Test
    public void postUserIdSizeOver() {
        String body = "{" +
                "\"id\": \"aaaaaaaaaaaaaaaaaaa\"," +
                "\"value\": \"test_post_value\"" +
                "}";

        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body(body)
                .post(BASE_PATH)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("Error.Message", equalTo("Bad Request"))
                .body("Error.Detail", equalTo("id: size must be between 0 and 18; "))
                .body("Error.Code", equalTo(""));

        List<Map<String, Object>> actualDataList = this.jdbcTemplate.queryForList(SELECT_SQL, "aaaaaaaaaaaaaaaaaaa");
        assertThat(actualDataList.size()).isEqualTo(0);
    }

    @Test
    public void postUserIdBlank() {
        String body = "{" +
                "\"id\": \"\"," +
                "\"value\": \"test_post_value\"" +
                "}";

        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body(body)
                .post(BASE_PATH)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("Error.Message", equalTo("Bad Request"))
                .body("Error.Detail", equalTo("id: must not be blank; "))
                .body("Error.Code", equalTo(""));

        List<Map<String, Object>> actualDataList = this.jdbcTemplate.queryForList(SELECT_SQL, "");
        assertThat(actualDataList.size()).isEqualTo(0);
    }

    @Test
    public void deleteUserById() {
        List<Map<String, Object>> actualDataList = this.jdbcTemplate.queryForList(SELECT_SQL, "test_id");
        assertThat(actualDataList.size()).isEqualTo(1);

        RestAssured.given()
                .basePath(BASE_PATH)
                .delete("/{user_id}", "test_id")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

        actualDataList = this.jdbcTemplate.queryForList(SELECT_SQL, "test_id");
        assertThat(actualDataList.size()).isEqualTo(0);
    }

    @Test
    public void deleteUserByIdNotFound() {
        RestAssured.given()
                .basePath(BASE_PATH)
                .delete("/{user_id}", "404_id")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("Error.Message", equalTo("Not Found"))
                .body("Error.Detail", equalTo(""))
                .body("Error.Code", equalTo(""));
    }
}
