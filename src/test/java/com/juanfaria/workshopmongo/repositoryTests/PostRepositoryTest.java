package com.juanfaria.workshopmongo.repositoryTests;

import com.juanfaria.workshopmongo.domain.Post;
import com.juanfaria.workshopmongo.dto.AuthorDto;
import com.juanfaria.workshopmongo.dto.CommentDto;
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
                new AuthorDto(userService.findAll().get(0)));
        Post obj2 = new Post(null, Instant.now(), "Bom dia!", "Acordei feliz hoje!",
               new AuthorDto(userService.findAll().get(0)));
        postRepository.saveAll(Arrays.asList(obj1, obj2));
        Assertions.assertTrue(obj1.getId()!=null && obj2.getId() != null);
    }

    @Test
    void testDelete(){
        Post obj = new Post(null, Instant.now(), "Partiu", "Vou viajar, abraço!",
                new AuthorDto(userService.findAll().get(0)));
        postRepository.save(obj);
        postRepository.deleteById(obj.getId());
        Optional<Post> objBeforeDelete = postRepository.findById(obj.getId());
        Assertions.assertTrue(objBeforeDelete.isEmpty());
    }

    @Test
    void testFindById(){
        Post obj = new Post(null, Instant.now(), "Partiu", "Vou viajar, abraço!",
                new AuthorDto(userService.findAll().get(0)));
        postRepository.save(obj);
        Assertions.assertEquals(obj, postRepository.findById(obj.getId()).get());
    }
    @Test
    void testInsertComments() {
        Post obj = new Post(null, Instant.now(), "Partiu jogo", "Vou jogar em São Paulo, abraço!",
                new AuthorDto(userService.findAll().get(0)));

        postRepository.save(obj);

        obj.getComments().add(new CommentDto("Boa sorte!",
                Instant.now(),
                new AuthorDto(userService.findAll().get(2))));
        CommentDto cm = obj.getComments().get(0);

        postRepository.save(obj);
        Optional<Post> obj2 = postRepository.findById(obj.getId());
        CommentDto cm2 = obj2.get().getComments().get(0);
        Assertions.assertEquals(cm.getText(), cm2.getText());
        Assertions.assertEquals(cm.getAuthorDto().getName(), cm2.getAuthorDto().getName());
        Assertions.assertEquals(cm.getAuthorDto().getId(), cm2.getAuthorDto().getId());


    }

}
