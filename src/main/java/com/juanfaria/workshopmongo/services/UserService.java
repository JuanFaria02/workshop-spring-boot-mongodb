package com.juanfaria.workshopmongo.services;

import com.juanfaria.workshopmongo.domain.User;
import com.juanfaria.workshopmongo.dto.UserDto;
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

    public User insert(User obj){
        return userRepository.insert(obj);
    }

    public void deleteById(String id){
        findById(id);
        userRepository.deleteById(id);
    }

    public User update(User obj){
        User newObj = findById(obj.getId());
        updateData(newObj, obj);
        return userRepository.save(newObj);
    }

    private void updateData(User newObj, User obj){
        newObj.setEmail(obj.getEmail());
        newObj.setName(obj.getName());
    }
    public User fromDto(UserDto userDto){
        return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
    }
}
