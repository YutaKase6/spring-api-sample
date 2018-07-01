package com.example.springapi.infrastructure;

import com.example.springapi.domain.object.User;
import com.example.springapi.infrastructure.entity.UserEntity;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserEntityTests {
    private final static String TEST_ID = "test_id";
    private final static String TEST_VALUE = "test_value";

    private User testUser;
    private UserEntity testUserEntity;

    @Before
    public void setup() {
        this.testUser = User.builder()
                .id(TEST_ID)
                .value(TEST_VALUE)
                .build();

        this.testUserEntity = UserEntity.builder()
                .id(TEST_ID)
                .value(TEST_VALUE)
                .build();
    }

    @Test
    public void buildTests() {
        UserEntity actual = UserEntity.build(this.testUser);

        assertThat(actual.getId()).isEqualTo(this.testUser.getId());
        assertThat(actual.getValue()).isEqualTo(this.testUser.getValue());
    }

    @Test
    public void toDomainTests() {
        User actual = this.testUserEntity.toDomainUser();

        assertThat(actual.getId()).isEqualTo(this.testUserEntity.getId());
        assertThat(actual.getValue()).isEqualTo(this.testUserEntity.getValue());
    }
}
