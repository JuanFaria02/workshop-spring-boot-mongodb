package com.juanfaria.workshopmongo.resources;

import com.juanfaria.workshopmongo.domain.User;
import com.juanfaria.workshopmongo.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.juanfaria.workshopmongo.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){

        List<User> users = userService.findAll();
        List<UserDto> listDto = users.stream().map(x -> new UserDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }


}
