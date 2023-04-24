package com.juanfaria.workshopmongo.services;

import com.juanfaria.workshopmongo.domain.Post;
import com.juanfaria.workshopmongo.repository.PostRepository;
import com.juanfaria.workshopmongo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post findById(String id){
        Optional<Post> obj = postRepository.findById(id);
        return obj.orElseThrow(()->new ObjectNotFoundException("Object not found"));
    }

    public List<Post> findByTitleContainingIgnoreCase(String title){
        List<Post> posts = postRepository.searchTitle(title);
        return posts;
    }

    public List<Post> fullSearch(String text, LocalDateTime minDate, LocalDateTime maxDate){
        maxDate = maxDate.plusDays(1);
        return postRepository.fullSearch(text, minDate, maxDate);
    }
}
