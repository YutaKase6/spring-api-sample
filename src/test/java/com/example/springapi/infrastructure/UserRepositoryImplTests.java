package com.example.springapi.infrastructure;

import com.example.springapi.domain.object.User;
import com.example.springapi.infrastructure.entity.UserEntity;
import com.example.springapi.infrastructure.repository.UserJpaRepository;
import com.example.springapi.infrastructure.repository.UserRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserRepositoryImplTests {
    private final static String TEST_ID = "test_id";
    private final static String TEST_VALUE = "test_value";

    private User testUser;
    private UserEntity testUserEntity;

    @Mock
    private UserJpaRepository userJpaRepository;
    @InjectMocks
    private UserRepositoryImpl userRepositoryImpl;


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
    public void findById() {

        when(this.userJpaRepository.findById(TEST_ID)).thenReturn(Optional.of(this.testUserEntity));

        User actual = this.userRepositoryImpl.findById(TEST_ID);

        assertThat(actual).isEqualTo(this.testUser);

        verify(this.userJpaRepository, times(1)).findById(TEST_ID);
    }

    @Test
    public void save() {

        when(this.userJpaRepository.save(this.testUserEntity)).thenReturn(this.testUserEntity);

        User actual = this.userRepositoryImpl.save(this.testUser);

        assertThat(actual).isEqualTo(this.testUser);

        verify(this.userJpaRepository, times(1)).save(this.testUserEntity);
    }

    @Test
    public void deleteById() {

        this.userRepositoryImpl.deleteById(TEST_ID);

        verify(this.userJpaRepository, times(1)).deleteById(TEST_ID);
    }
}
