package com.example.springapi.infrastructure;

import com.example.springapi.domain.object.User;
import com.example.springapi.infrastructure.user.entity.UserEntity;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserEntityTests {
    private final static String TEST_ID = "test_id";
    private final static String TEST_VALUE = "test_value";

    private User expectedUser;
    private UserEntity expectedUserEntity;

    @Before
    public void setup() {
        this.expectedUser = User.builder()
                .id(TEST_ID)
                .value(TEST_VALUE)
                .build();

        this.expectedUserEntity = UserEntity.builder()
                .id(TEST_ID)
                .value(TEST_VALUE)
                .build();
    }

    @Test
    public void buildTests() {
        UserEntity actual = UserEntity.build(this.expectedUser);

        assertThat(actual.getId()).isEqualTo(this.expectedUser.getId());
        assertThat(actual.getValue()).isEqualTo(this.expectedUser.getValue());
    }

    @Test
    public void toDomainTests() {
        User actual = this.expectedUserEntity.toDomainUser();

        assertThat(actual.getId()).isEqualTo(this.expectedUserEntity.getId());
        assertThat(actual.getValue()).isEqualTo(this.expectedUserEntity.getValue());
    }
}
