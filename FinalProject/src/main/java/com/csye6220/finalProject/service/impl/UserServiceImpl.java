package com.csye6220.finalProject.service.impl;

import com.csye6220.finalProject.dao.CommunityDAO;
import com.csye6220.finalProject.dao.UserDAO;
import com.csye6220.finalProject.exception.ResourceNotFoundException;
import com.csye6220.finalProject.exception.ValidationException;
import com.csye6220.finalProject.model.Community;
import com.csye6220.finalProject.model.User;
import com.csye6220.finalProject.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final CommunityDAO communityDAO;
    public UserServiceImpl(UserDAO userDAO, CommunityDAO communityDAO) {
        this.userDAO = userDAO;
        this.communityDAO = communityDAO;
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
    public User updateUser(String email, long id) {
        User existingUser = userDAO.getUserById(id);
        if(existingUser == null){
            throw  new ValidationException("User not found with id: " + id);
        }
//        existingUser.setUsername(username);
        existingUser.setEmail(email);
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

    @Override
    public void updatePassword(String currentPass, String newPass, String username) {
        User user = userDAO.findByUserName(username);
        String hashPass = passwordEncoder.encode(newPass);
        if (!validateCurrentPassword(user, currentPass)) {
            throw new ValidationException("Current password doesn't match");
        }
        System.out.println(hashPass);
        user.setPassword(hashPass);
        userDAO.updateUser(user);
    }

    @Override
    public void joinCommunity(String username, long communityId) {

        try{
            User user = userDAO.findByUserName(username);
            Community community = communityDAO.findByCommunityId(communityId);

            if (user != null && community != null) {
                user.getCommunities().add(community);
                community.getMembers().add(user);
                userDAO.updateUser(user);
            } else {
                throw new RuntimeException("User or community not found");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void leaveCommunity(String username, long communityId) {
        User user = userDAO.findByUserName(username);
        Community community = communityDAO.findByCommunityId(communityId);
        if (user != null && community != null) {

            List<User> existingMembers = community.getMembers();
            for(User c : existingMembers){
                System.out.println(c.getUsername());
            }
            existingMembers.remove(user);
            communityDAO.update(community);
            for(User c : existingMembers){
                System.out.println(c.getUsername());
            }

//            userDAO.leaveCommunity(user.getUserId());
            List<Community> existingCommunities = user.getCommunities();
            for(Community u : existingCommunities){
                System.out.println(u.getName());
            }
            existingCommunities.remove(community);
            userDAO.updateUser(user);
            for(Community u : existingCommunities){
                System.out.println(u.getName());
            }
        } else {
            throw new RuntimeException("User or community not found");
        }

    }

    private boolean validateCurrentPassword(User user, String currentPassword) {
        String encodedPassword = user.getPassword();
        return passwordEncoder.matches(currentPassword, encodedPassword);
    }
}