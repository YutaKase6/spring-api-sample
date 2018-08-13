package com.example.springapi.domain;

import com.example.springapi.domain.object.User;
import com.example.springapi.domain.repository.UserRepository;
import com.example.springapi.domain.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceTests {

    private final static String TEST_ID = "test_id";
    private final static String TEST_VALUE = "test_value";

    private User testUser;

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Before
    public void setup() {
        this.testUser = User.builder()
                .id(TEST_ID)
                .value(TEST_VALUE)
                .build();
    }

    @Test
    public void findById() {

        when(this.userRepository.findById(TEST_ID)).thenReturn(this.testUser);

        User actual = this.userService.findById(TEST_ID);

        assertThat(actual).isEqualTo(this.testUser);

        verify(this.userRepository, times(1)).findById(TEST_ID);
    }

    @Test
    public void save() {

        when(this.userRepository.save(this.testUser)).thenReturn(this.testUser);

        User actual = this.userService.save(this.testUser);

        assertThat(actual).isEqualTo(this.testUser);

        verify(this.userRepository, times(1)).save(this.testUser);
    }

    @Test
    public void deleteById() {

        this.userService.deleteById(TEST_ID);

        verify(this.userRepository, times(1)).deleteById(TEST_ID);
    }
}
