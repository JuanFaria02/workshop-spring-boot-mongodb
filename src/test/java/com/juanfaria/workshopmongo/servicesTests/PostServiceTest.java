package com.juanfaria.workshopmongo.servicesTests;

import com.juanfaria.workshopmongo.domain.Post;
import com.juanfaria.workshopmongo.services.PostService;
import com.juanfaria.workshopmongo.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PostServiceTest {
    @Autowired
    private PostService postService;
    @Test
    void findByIdTest(){

        Assertions.assertNotNull(postService.findById("643eace70f0eb63184df869c"));
        Assertions.assertThrows(ObjectNotFoundException.class, ()->postService.findById("aas3rrt"));
    }

    @Test
    void findByTitleTest(){
        List<Post> posts = postService.findByTitleContainingIgnoreCase("Partiu");

        Assertions.assertEquals(postService.findById("643eace70f0eb63184df869c").getId(), posts.get(0).getId());
    }
}
