package com.csye6220.finalProject.dao;

import com.csye6220.finalProject.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getAllUser();
    void deletUser(String userId);
    void addUser(User user);
    User getUserById(String userId);

    User getUserByemailId(String emailId);
}
