package com.csye6220.finalProject.service.impl;

import com.csye6220.finalProject.exception.ResourceNotFoundException;
import com.csye6220.finalProject.model.User;
import com.csye6220.finalProject.repository.UserRepository;
import com.csye6220.finalProject.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        super();
        this.userRepository = userRepository;
    }
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public User getUserById(long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }else {
            throw new ResourceNotFoundException("user", "id", id);
        }
    }
}
