package com.juanfaria.workshopmongo.servicesTests;

import com.juanfaria.workshopmongo.domain.User;
import com.juanfaria.workshopmongo.repository.UserRepository;
import com.juanfaria.workshopmongo.services.UserService;
import com.juanfaria.workshopmongo.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Test
    void testInsert(){
        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        userService.insert(maria);
        Assertions.assertTrue(maria.getId()!=null);
    }

    @Test
    void testDelete(){
        User jose = new User(null, "Jose Brown", "jose@gmail.com");
        userService.insert(jose);
        userService.deleteById(jose.getId());
        Assertions.assertThrows(ObjectNotFoundException.class, ()->userService.findById(jose.getId()));
    }

    @Test
    void testFindById(){
        User jose = new User(null, "Jose Brown", "jose@gmail.com");
        userService.insert(jose);
        Assertions.assertEquals(jose, userService.findById(jose.getId()));
        Assertions.assertThrows(ObjectNotFoundException.class, ()->userService.findById("aas3rrt"));
    }

    @Test
    void testFindAll(){
        Assertions.assertEquals(userService.findAll(), userRepository.findAll());
    }

    @Test
    void testUpdate(){
        User objOld = userService.findById("643d4c3ee50f1b185c33d831");
        User objNew = new User("643d4c3ee50f1b185c33d831", "Jonah Rest", "jonah@gmail.com");
        Assertions.assertEquals(objOld, objNew);
    }
}
