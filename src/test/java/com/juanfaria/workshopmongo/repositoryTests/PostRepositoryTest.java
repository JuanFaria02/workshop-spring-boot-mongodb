package com.juanfaria.workshopmongo.repositoryTests;

import com.juanfaria.workshopmongo.domain.Post;
import com.juanfaria.workshopmongo.domain.User;
import com.juanfaria.workshopmongo.dto.AuthorDto;
import com.juanfaria.workshopmongo.dto.CommentDto;
import com.juanfaria.workshopmongo.repository.PostRepository;
import com.juanfaria.workshopmongo.repository.UserRepository;
import com.juanfaria.workshopmongo.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    private Post testPost;
    private User testUser;
    @BeforeEach
    void setup() {
        testUser = new User(null, "Test User", "testuser@example.com");

        userRepository.insert(testUser);

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
    void testInsert(){
        Post post = new Post(null, LocalDateTime.now(), "Lorem", "Test, lorem!",
                new AuthorDto(testUser));
        postRepository.saveAll(Arrays.asList(post));

        Assertions.assertTrue(post.getId()!=null);
    }

    @Test
    void testDelete(){

        postRepository.deleteById(testPost.getId());
        Optional<Post> objBeforeDelete = postRepository.findById(testPost.getId());
        Assertions.assertTrue(objBeforeDelete.isEmpty());
    }

    @Test
    void testFindById(){
        Optional<Post> post = postRepository.findById(testPost.getId());
        Assertions.assertEquals(testPost.getId(), post.get().getId());
    }

    @Test
    void testInsertComments() {

        testPost.getComments().add(new CommentDto("Boa sorte!",
                LocalDateTime.now(),
                new AuthorDto(testUser)));

        postRepository.save(testPost);
        CommentDto commentDto = testPost.getComments().get(0);

        Optional<Post> obj = postRepository.findById(testPost.getId());
        CommentDto commentDtoNew = obj.get().getComments().get(0);

        Assertions.assertEquals(commentDto.getText(), commentDtoNew.getText());
        Assertions.assertEquals(commentDto.getAuthorDto().getName(), commentDtoNew.getAuthorDto().getName());
        Assertions.assertEquals(commentDto.getAuthorDto().getId(), commentDtoNew.getAuthorDto().getId());
    }
    @Test
    void testFindByTitle(){
        Optional<Post> post = postRepository.findById(testPost.getId());
        List<Post> posts = postRepository.findByTitleContainingIgnoreCase("Lorem");
        Assertions.assertEquals(posts.get(0).getId(), post.get().getId());
    }

}
