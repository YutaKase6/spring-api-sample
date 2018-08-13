package com.example.springapi.db;

import com.example.springapi.infrastructure.entity.UserEntity;
import com.example.springapi.infrastructure.repository.UserJpaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class dbTests {

    private final static String TEST_ID = "test_id";
    private final static String TEST_VALUE = "test_value";

    private UserEntity testUserEntity;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserJpaRepository userRepository;

    @Before
    public void setup() {
        this.testUserEntity = UserEntity.builder()
                .id(TEST_ID)
                .value(TEST_VALUE)
                .build();
    }

    @Test
    public void testFindById() {
        UserEntity actual = this.userRepository.findById(TEST_ID).get();

        assertThat(actual).isEqualTo(this.testUserEntity);
    }

    @Test
    public void testSave() {
        UserEntity saved = this.userRepository.save(this.testUserEntity);

        UserEntity actual = this.testEntityManager.find(UserEntity.class, TEST_ID);

        assertThat(actual).isEqualTo(this.testUserEntity);
        assertThat(actual).isEqualTo(saved);
    }

    @Test
    public void testDeleteById() {
        this.userRepository.deleteById(TEST_ID);

        UserEntity actual = this.testEntityManager.find(UserEntity.class, TEST_ID);

        assertThat(actual).isNull();
    }
}
