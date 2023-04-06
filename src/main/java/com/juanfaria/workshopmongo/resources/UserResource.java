package com.juanfaria.workshopmongo.resources;

import com.juanfaria.workshopmongo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.juanfaria.workshopmongo.services.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> findAll(){

        List<User> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }


}
