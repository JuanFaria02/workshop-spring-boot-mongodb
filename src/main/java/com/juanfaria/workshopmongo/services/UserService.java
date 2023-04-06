package com.juanfaria.workshopmongo.services;

import com.juanfaria.workshopmongo.domain.User;
import com.juanfaria.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> findAll(){
        List<User> users = userRepository.findAll();
        return users;
    }


}
