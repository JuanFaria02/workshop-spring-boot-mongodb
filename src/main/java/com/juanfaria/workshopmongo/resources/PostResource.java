package com.juanfaria.workshopmongo.resources;

import com.juanfaria.workshopmongo.domain.Post;
import com.juanfaria.workshopmongo.resources.util.URL;
import com.juanfaria.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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

    @GetMapping("/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(@RequestParam(value="text", defaultValue = "") String text,
                                                 @RequestParam(value="minDate", defaultValue = "") String minDate,
                                                 @RequestParam(value="maxDate", defaultValue = "") String maxDate){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        text = URL.decodeParam(text);
        LocalDateTime min = URL.convertDate(minDate, LocalDate.parse("1970-01-01", fmt).atStartOfDay());
        LocalDateTime max = URL.convertDate(maxDate, LocalDateTime.now().toLocalDate().atStartOfDay());
        List<Post> obj = postService.fullSearch(text, min, max);
        return ResponseEntity.ok().body(obj);
    }
}
