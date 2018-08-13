package com.example.springapi.application;

import com.example.springapi.application.controller.UserController;
import com.example.springapi.application.resource.UserBody;
import com.example.springapi.domain.object.User;
import com.example.springapi.domain.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserControllerTests {
    private final static String TEST_ID = "test_id";
    private final static String TEST_VALUE = "test_value";

    private User testUser;
    private UserBody testUserBody;

    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;


    @Before
    public void setup() {
        this.testUser = User.builder()
                .id(TEST_ID)
                .value(TEST_VALUE)
                .build();

        this.testUserBody = new UserBody();
        this.testUserBody.setId(TEST_ID);
        this.testUserBody.setValue(TEST_VALUE);
    }

    @Test
    public void findById() {

        when(this.userService.findById(TEST_ID)).thenReturn(this.testUser);

        User actual = this.userController.findById(TEST_ID);

        assertThat(actual).isEqualTo(this.testUser);

        verify(this.userService, times(1)).findById(TEST_ID);
    }

    @Test
    public void save() {

        when(this.userService.save(this.testUser)).thenReturn(this.testUser);

        User actual = this.userController.save(this.testUserBody);

        assertThat(actual).isEqualTo(this.testUser);

        verify(this.userService, times(1)).save(this.testUser);
    }

    @Test
    public void deleteById() {

        this.userController.deleteById(TEST_ID);

        verify(this.userService, times(1)).deleteById(TEST_ID);
    }
}
