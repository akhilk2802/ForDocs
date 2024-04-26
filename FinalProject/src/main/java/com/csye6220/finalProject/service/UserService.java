package com.csye6220.finalProject.service;

import com.csye6220.finalProject.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    List<User> getAllUsers();
    User getUserById(long id);
    User getUserByUsername(String name);
    User updateUser(String email, long id);
    void deleteUser(long id);
    void updatePassword(String currentPass, String newPass, String username);
    void joinCommunity(String username, long communityId);
    void leaveCommunity(String username, long communityId);
}
