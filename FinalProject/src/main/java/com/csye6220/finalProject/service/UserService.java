package com.csye6220.finalProject.service;

import com.csye6220.finalProject.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    List<User> getAllUsers();
    User getUserById(long id);
}
