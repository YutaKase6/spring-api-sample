package com.example.springapi.application;

import com.example.springapi.application.resource.UserBody;
import com.example.springapi.domain.object.User;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserBodyTests {
    private final static String TEST_ID = "test_id";
    private final static String TEST_VALUE = "test_value";

    private UserBody testUserBody;

    @Before
    public void setup() {
        this.testUserBody = new UserBody();
        this.testUserBody.setId(TEST_ID);
        this.testUserBody.setValue(TEST_VALUE);
    }

    @Test
    public void toDomainTests() {
        User actual = this.testUserBody.toDomainUser();

        assertThat(actual.getId()).isEqualTo(this.testUserBody.getId());
        assertThat(actual.getValue()).isEqualTo(this.testUserBody.getValue());
    }
}
