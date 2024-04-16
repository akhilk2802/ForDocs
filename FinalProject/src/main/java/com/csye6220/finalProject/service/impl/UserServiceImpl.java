package com.csye6220.finalProject.service.impl;

import com.csye6220.finalProject.dao.UserDAO;
import com.csye6220.finalProject.exception.ResourceNotFoundException;
import com.csye6220.finalProject.exception.ValidationException;
import com.csye6220.finalProject.model.User;
import com.csye6220.finalProject.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    public User saveUser(User user) {
        User userToAdd = userDAO.addUser(user);
        if(userToAdd == null){
            throw new ValidationException("Could not add or the fields are empty");
        }
        return userToAdd;
    }
    @Override
    public List<User> getAllUsers() {
        List<User> users = userDAO.getAllUser();
        if (users == null){
            throw new ResourceNotFoundException("Users not found");
        }else{
            return users;
        }
    }
    @Override
    public User getUserById(long id){
        User user = userDAO.getUserById(id);
        if(user == null){
            throw new ResourceNotFoundException("User Not Found with id: " + id);
        }
        return user;
    }

    @Override
    public User getUserByUsername(String name) {
        User user = userDAO.findByUserName(name);
        if(user == null){
            throw new ResourceNotFoundException("User Not found with name: " + name);
        }
        return user;
    }

    @Override
    public User updateUser(User user, long id) {
        User existingUser = userDAO.getUserById(id);
        if(existingUser == null){
            throw  new ValidationException("User not found with id: " + id);
        }
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());
        return userDAO.updateUser(existingUser);
    }
    public void deleteUser(long id) {
        User user = userDAO.getUserById(id);
        if(user == null){
            throw new ResourceNotFoundException("User Not Found with id: " + id);
        }else {
            userDAO.deleteUser(id);
        }
    }
}