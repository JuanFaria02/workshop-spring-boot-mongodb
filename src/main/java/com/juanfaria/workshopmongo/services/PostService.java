package com.juanfaria.workshopmongo.services;

import com.juanfaria.workshopmongo.domain.Post;
import com.juanfaria.workshopmongo.repository.PostRepository;
import com.juanfaria.workshopmongo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
