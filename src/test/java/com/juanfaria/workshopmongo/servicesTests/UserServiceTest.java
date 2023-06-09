package com.juanfaria.workshopmongo.servicesTests;

import com.juanfaria.workshopmongo.domain.Post;
import com.juanfaria.workshopmongo.domain.User;
import com.juanfaria.workshopmongo.dto.AuthorDto;
import com.juanfaria.workshopmongo.repository.PostRepository;
import com.juanfaria.workshopmongo.repository.UserRepository;
import com.juanfaria.workshopmongo.services.UserService;
import com.juanfaria.workshopmongo.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PostRepository postRepository;

    private User testUser;

    @BeforeEach
    void setup() {
        testUser = new User(null, "Test User", "testuser@example.com");
        userRepository.save(testUser);
    }

    @AfterEach
    void cleanup() {
        userRepository.deleteAll();
        postRepository.deleteAll();
    }
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
        User objOld = userService.findById(testUser.getId());
        User objNew = new User(testUser.getId(), "Jonah Rest", "jonah@gmail.com");
        Assertions.assertEquals(objOld, objNew);
    }

    @Test
    void testReferencePost(){
        User obj = userService.findById(testUser.getId());

        Post post = new Post(null, LocalDateTime.now(), "Lorem", "Vou viajar, abraço!",
                new AuthorDto(obj));
        postRepository.save(post);
        obj.getPosts().add(post);

        userRepository.save(obj);
        Assertions.assertEquals(userService.findById(testUser.getId()).getPosts().get(0), post);
    }
}
