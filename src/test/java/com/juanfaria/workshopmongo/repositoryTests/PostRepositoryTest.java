package com.juanfaria.workshopmongo.repositoryTests;

import com.juanfaria.workshopmongo.domain.Post;
import com.juanfaria.workshopmongo.repository.PostRepository;
import com.juanfaria.workshopmongo.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;
    @Test
    void testInsert(){
        Post obj1 = new Post(null, Instant.now(), "Partiu Viajem", "Vou viajar para São Paulo, abraço!",
                userService.findAll().get(0));
        Post obj2 = new Post(null, Instant.now(), "Bom dia!", "Acordei feliz hoje!",
                userService.findAll().get(0));
        postRepository.saveAll(Arrays.asList(obj1, obj2));
        Assertions.assertTrue(obj1.getId()!=null && obj2.getId() != null);
    }

    @Test
    void testDelete(){
        Post obj = new Post(null, Instant.now(), "Partiu", "Vou viajar, abraço!",
                userService.findAll().get(0));
        postRepository.save(obj);
        postRepository.deleteById(obj.getId());
        Optional<Post> objBeforeDelete = postRepository.findById(obj.getId());
        Assertions.assertTrue(objBeforeDelete.isEmpty());
    }

    @Test
    void testFindById(){
        Post obj = new Post(null, Instant.now(), "Partiu", "Vou viajar, abraço!",
                userService.findAll().get(0));
        postRepository.save(obj);
        Assertions.assertEquals(obj, postRepository.findById(obj.getId()).get());
    }

}
