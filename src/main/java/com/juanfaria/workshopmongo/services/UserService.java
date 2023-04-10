package com.juanfaria.workshopmongo.services;

import com.juanfaria.workshopmongo.domain.User;
import com.juanfaria.workshopmongo.repository.UserRepository;
import com.juanfaria.workshopmongo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> findAll(){
        List<User> users = userRepository.findAll();
        return users;
    }

    public User findById(String id){
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
    }
}
