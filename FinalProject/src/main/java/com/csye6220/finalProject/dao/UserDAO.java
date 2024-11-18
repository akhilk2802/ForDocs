package com.csye6220.finalProject.dao;

import com.csye6220.finalProject.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getAllUser();
    void deleteUser(long userId);
    User addUser(User user);
    User getUserById(long userId);
    User findByUserName(String username);
    User updateUser(User user);
    void leaveCommunity(long userId);
}
