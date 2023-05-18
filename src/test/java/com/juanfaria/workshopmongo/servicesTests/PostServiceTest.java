package com.juanfaria.workshopmongo.servicesTests;

import com.juanfaria.workshopmongo.domain.Post;
import com.juanfaria.workshopmongo.domain.User;
import com.juanfaria.workshopmongo.dto.AuthorDto;
import com.juanfaria.workshopmongo.repository.PostRepository;
import com.juanfaria.workshopmongo.repository.UserRepository;
import com.juanfaria.workshopmongo.services.PostService;
import com.juanfaria.workshopmongo.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class PostServiceTest {
    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    private User testUser;
    private Post testPost;

    @BeforeEach
    void setup() {
        testUser = new User(null, "Test User", "testuser@example.com");

        userRepository.save(testUser);
        testPost = new Post(null, LocalDateTime.now(), "Lorem", "Test, lorem!",
                new AuthorDto(testUser));
        postRepository.save(testPost);
        testUser.getPosts().add(testPost);

        userRepository.save(testUser);
    }

    @AfterEach
    void cleanup() {
        userRepository.deleteAll();
        postRepository.deleteAll();
    }
    @Test
    void findByIdTest(){
        Post post = postService.findById(testPost.getId());
        Assertions.assertNotNull(post);
        Assertions.assertThrows(ObjectNotFoundException.class, ()->postService.findById("aas3rrt"));
    }

    @Test
    void findByTitleTest(){
        List<Post> posts = postService.findByTitleContainingIgnoreCase(testPost.getTitle());

        Assertions.assertEquals(postService.findById(testPost.getId()).getId(), posts.get(0).getId());
    }
}
