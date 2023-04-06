package com.juanfaria.workshopmongo.resources;

import com.juanfaria.workshopmongo.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        User maria = new User(null, "Maria Silva", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        List<User> users = new ArrayList<>();
        users.addAll(Arrays.asList(maria, alex));
        return ResponseEntity.ok().body(users);
    }


}
