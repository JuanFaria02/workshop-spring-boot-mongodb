package com.juanfaria.workshopmongo.resources;

import com.juanfaria.workshopmongo.domain.Post;
import com.juanfaria.workshopmongo.resources.util.URL;
import com.juanfaria.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostResource {

    @Autowired
    private PostService postService;
    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
        Post obj = postService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/textsearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue = "") String text){
        text = URL.decodeParam(text);
        List<Post> obj = postService.findByTitleContainingIgnoreCase(text);
        return ResponseEntity.ok().body(obj);
    }
}
