package com.juanfaria.workshopmongo.repositoryTests;

import com.juanfaria.workshopmongo.domain.User;
import com.juanfaria.workshopmongo.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    private User testUser;

    @BeforeEach
    void setup() {
        testUser = new User(null, "Test User", "testuser@example.com");
        userRepository.save(testUser);
    }

    @AfterEach
    void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    void testInsert(){

        userRepository.saveAll(Arrays.asList(testUser));
        Assertions.assertTrue(testUser.getId()!=null);
    }

    @Test
    void testDelete(){
        userRepository.deleteById(testUser.getId());
        Optional<User> objBeforeDelete = userRepository.findById(testUser.getId());
        Assertions.assertTrue(objBeforeDelete.isEmpty());
    }

    @Test
    void testFindById(){
        User obj = new User(null, "Steph Brown", "steph@gmail.com");
        userRepository.save(obj);
        Assertions.assertEquals(obj, userRepository.findById(obj.getId()).get());
    }

}
