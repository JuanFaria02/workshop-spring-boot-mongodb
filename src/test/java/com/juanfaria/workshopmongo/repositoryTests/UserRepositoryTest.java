package com.juanfaria.workshopmongo.repositoryTests;

import com.juanfaria.workshopmongo.domain.User;
import com.juanfaria.workshopmongo.repository.UserRepository;
import com.juanfaria.workshopmongo.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    void testInsert(){
        User obj1 = new User(null, "Daniel Ecos", "daniel@gmail.com");
        User obj2 = new User(null, "Otavio Curry", "ota@gmail.com");
        userRepository.saveAll(Arrays.asList(obj1, obj2));
        Assertions.assertTrue(obj1.getId()!=null && obj2.getId() != null);
    }

    @Test
    void testDelete(){
        User obj = new User(null, "Jose Brown", "jose@gmail.com");
        userRepository.save(obj);
        userRepository.deleteById(obj.getId());
        Optional<User> objBeforeDelete = userRepository.findById(obj.getId());
        Assertions.assertTrue(objBeforeDelete.isEmpty());
    }

    @Test
    void testFindById(){
        User obj = new User(null, "Steph Brown", "steph@gmail.com");
        userRepository.save(obj);
        Assertions.assertEquals(obj, userRepository.findById(obj.getId()).get());
    }

}
